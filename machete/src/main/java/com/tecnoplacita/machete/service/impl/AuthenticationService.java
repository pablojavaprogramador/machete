package com.tecnoplacita.machete.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tecnoplacita.machete.dto.LoginUserDto;
import com.tecnoplacita.machete.dto.RegisterUserDto;
import com.tecnoplacita.machete.exceptions.CorreoNoEncontrado;
import com.tecnoplacita.machete.exceptions.TokenInvalidException;
import com.tecnoplacita.machete.exceptions.TokenInvalidResetException;
import com.tecnoplacita.machete.model.TokenVerificacion;
import com.tecnoplacita.machete.model.User;
import com.tecnoplacita.machete.repository.TokenVerificacionRepository;
import com.tecnoplacita.machete.repository.UserRepository;
import com.tecnoplacita.machete.utils.TokenGenerator;

import jakarta.mail.internet.MimeMessage;

@Service
public class AuthenticationService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;

	@Autowired
	private TokenVerificacionRepository tokenVerificacionRepository;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	public AuthenticationService(UserRepository userRepository, AuthenticationManager authenticationManager,
			PasswordEncoder passwordEncoder) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public User signup(RegisterUserDto input) {
		if (!input.isAceptoAvisoPrivacidad()) {
			throw new IllegalArgumentException("Debe aceptar el aviso de privacidad.");
		}

		// Verificar si el usuario ya existe
		Optional<User> existingUser = userRepository.findByEmail(input.getEmail());
		if (existingUser.isPresent()) {
			throw new IllegalArgumentException("El correo electrónico ya está registrado.");
		}

		User user = new User();
		user.setUsuario(input.getUsuario());
		user.setEmail(input.getEmail());
		user.setPassword(passwordEncoder.encode(input.getPassword()));
		user.setHabilitado(false);
		user.setAvisoPrivacidadAceptado(input.isAceptoAvisoPrivacidad());

		User usuario = userRepository.save(user);

		// Generar un token de verificación
		TokenGenerator tokenDigitos = new TokenGenerator();
		String token = tokenDigitos.generateToken();

		TokenVerificacion tokenVerificacion = new TokenVerificacion(token, usuario);
		tokenVerificacion.setFechaExpiracion(LocalDateTime.now().plusHours(24)); // Token válido por 24 horas
		tokenVerificacionRepository.save(tokenVerificacion);

		// Enviar correo de verificación
		enviarCorreoVerificacion(usuario.getEmail(), token);

		return usuario;
	}

	private void enviarCorreoVerificacion(String email, String token) {
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

			String enlaceVerificacion = "https://mibolsillo-fya4h9bpbwe0dtg0.mexicocentral-01.azurewebsites.net/verificar?token="
					+ token;
			String mensajeHtml = "<html>" + "<body>" + "<h2>¡Verifica tu cuenta!</h2>"
					+ "<p>Gracias por registrarte en nuestra aplicación. Para completar el proceso de registro, haz clic en el siguiente enlace o ingresa el código de seguridad en la aplicación:</p>"
					+ "<p><strong>Código de seguridad: " + token + "</strong></p>" + "<a href='" + enlaceVerificacion
					+ "'>Verificar cuenta</a>" + "<br><br>"
					+ "<p><em>Este código es único y no debe compartirse con nadie. Si no solicitaste este registro, ignora este mensaje.</em></p>"
					+ "<p>¡Bienvenido a Mi Bolsillo!</p>" + "<p>Saludos,<br>El equipo de Mi Bolsillo</p>" + "</body>"
					+ "</html>";

			// Configurar el encabezado 'From'
			helper.setFrom("tecnoplacita@gmail.com"); // Reemplaza con tu dirección de correo verificada

			helper.setTo(email);
			helper.setSubject("Verifica tu cuenta con el código de seguridad");
			helper.setText(mensajeHtml, true); // true indica que es contenido HTML

			javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace(); // Reemplaza con un sistema de logging adecuado
			// Puedes lanzar una excepción personalizada o manejar el error según tu lógica
			// de negocio
		}
	}

	public User authenticate(LoginUserDto input) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword()));

		return userRepository.findByEmail(input.getEmail())
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
	}

	public boolean sendPasswordResetEmail(String email) throws CorreoNoEncontrado {
		User user = userRepository.findByEmail(email).orElse(null);

		if (user == null) {
			 throw new CorreoNoEncontrado("Correo electrónico no encontrado.");
			
		}

		TokenGenerator tokenDigitos = new TokenGenerator();
		String token = tokenDigitos.generateToken();
		Optional<TokenVerificacion> optionalTokenVerificacion = tokenVerificacionRepository
				.findByUsuarioId(user.getId());

		TokenVerificacion tokenVerificacion;
		if (optionalTokenVerificacion.isPresent()) {
			// Si el Optional contiene un valor, obtén el TokenVerificacion existente
			tokenVerificacion = optionalTokenVerificacion.get();
			tokenVerificacion.setToken(token); // Actualiza el token
			tokenVerificacion.setFechaExpiracion(LocalDateTime.now().plusHours(1)); // Actualiza la fecha de expiración
		} else {
			// Si el Optional está vacío, crea un nuevo TokenVerificacion
			tokenVerificacion = new TokenVerificacion(token, user);
			tokenVerificacion.setFechaExpiracion(LocalDateTime.now().plusHours(1)); // Establece la fecha de expiración
		}

		tokenVerificacionRepository.save(tokenVerificacion);

		// Enviar correo de recuperación
		enviarCorreoRecuperacion(user.getEmail(), token);

		return true;
	}

	private void enviarCorreoRecuperacion(String email, String token) {
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

			String enlaceRestablecimiento = "https://mibolsillo-fya4h9bpbwe0dtg0.mexicocentral-01.azurewebsites.net/auth/confirm-reset?token="
					+ token;
			String mensajeHtml = "<html>" + "<body>" + "<h2>Recupera tu cuenta</h2>"
					+ "<p>Has solicitado recuperar tu cuenta. Para completar el proceso, haz clic en el siguiente enlace o ingresa el código de seguridad en la aplicación:</p>"
					+ "<p><strong>Código de seguridad: " + token + "</strong></p>" + "<a href='"
					+ enlaceRestablecimiento + "'>Restablecer contraseña</a>" + "<br><br>"
					+ "<p><em>Este código es único y no debe compartirse con nadie. Si no solicitaste la recuperación de cuenta, ignora este mensaje.</em></p>"
					+ "<p>Saludos,<br>El equipo de Mi Bolsillo</p>" + "</body>" + "</html>";

			// Configurar el encabezado 'From'
			helper.setFrom("tecnoplacita@gmail.com"); // Reemplaza con tu dirección de correo verificada

			helper.setTo(email);
			helper.setSubject("Recupera tu cuenta con el código de seguridad");
			helper.setText(mensajeHtml, true); // true indica que es contenido HTML

			javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace(); // Reemplaza con un sistema de logging adecuado
			// Puedes lanzar una excepción personalizada o manejar el error según tu lógica
			// de negocio
		}
	}

	public boolean resetPassword(String token, String newPassword) throws TokenInvalidResetException {
		TokenVerificacion tokenVerificacion = tokenVerificacionRepository.findByToken(token);

		if (tokenVerificacion == null) {
			
			throw new TokenInvalidResetException("Token inválido");

		}

		if (tokenVerificacion.getFechaExpiracion().isBefore(LocalDateTime.now())) {
			throw new TokenInvalidResetException("El Token Expiro");
		}

		User user = tokenVerificacion.getUsuario();
		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);

		// Opcional: Eliminar el token después de usarlo
		tokenVerificacionRepository.delete(tokenVerificacion);
		return true;
	}
}
