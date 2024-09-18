package com.tecnoplacita.machete.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tecnoplacita.machete.dto.LoginUserDto;
import com.tecnoplacita.machete.dto.RegisterUserDto;
import com.tecnoplacita.machete.model.TokenVerificacion;
import com.tecnoplacita.machete.model.User;
import com.tecnoplacita.machete.repository.TokenVerificacionRepository;
import com.tecnoplacita.machete.repository.UserRepository;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    private TokenVerificacionRepository tokenVerificacionRepository;

    @Autowired
    private JavaMailSender javaMailSender;
    
    public AuthenticationService(
        UserRepository userRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {
    	  if (!input.isAceptoAvisoPrivacidad()) {
    	        throw new IllegalArgumentException("Debe aceptar el aviso de privacidad.");
    	    }
    	  
        User user = new User();
        user.setUsuario(input.getUsuario());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setHabilitado(false);
        user.setAvisoPrivacidadAceptado(input.isAceptoAvisoPrivacidad());

        User usuario = userRepository.save(user);

        // Generar un token de verificación
        String token = UUID.randomUUID().toString();
        TokenVerificacion tokenVerificacion = new TokenVerificacion(token, usuario);
        tokenVerificacionRepository.save(tokenVerificacion);

        // Enviar correo de verificación
        enviarCorreoVerificacion(usuario.getEmail(), token);

        return usuario;
    }
    private void enviarCorreoVerificacion(String email, String token) {
        String enlaceVerificacion = "http://192.168.1.11:9090/verificar?token=" + token;
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(email);
        mensaje.setSubject("Verificación de cuenta");
        mensaje.setText("Haga clic en el siguiente enlace para verificar su cuenta: " + enlaceVerificacion);
        javaMailSender.send(mensaje);
    }
    
    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found")); // Proveedor de excepción
    }
    
    
    
    public boolean sendPasswordResetEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            return false;
        }

        String token = UUID.randomUUID().toString();
        Optional<TokenVerificacion> optionalTokenVerificacion = tokenVerificacionRepository.findByUsuarioId(user.getId());

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

        String enlaceRestablecimiento = "http://192.168.1.11:9090/auth/confirm-reset?token=" + token;
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(email);
        mensaje.setSubject("Restablecimiento de contraseña");
        mensaje.setText("Haga clic en el siguiente enlace para restablecer su contraseña: " + enlaceRestablecimiento);
        javaMailSender.send(mensaje);

        return true;
    }


    public boolean resetPassword(String token, String newPassword) {
        TokenVerificacion tokenVerificacion = tokenVerificacionRepository.findByToken(token);

        if (tokenVerificacion == null || tokenVerificacion.getFechaExpiracion().isBefore(LocalDateTime.now())) {
            return false;
        }

        User user = tokenVerificacion.getUsuario();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        // Opcional: Eliminar el token después de usarlo
        tokenVerificacionRepository.delete(tokenVerificacion);

        return true;
    }
    
    
}
