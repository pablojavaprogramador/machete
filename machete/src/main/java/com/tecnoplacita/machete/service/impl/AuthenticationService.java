package com.tecnoplacita.machete.service.impl;

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
    	
        User user = new User();
        user.setUsuario(input.getUsuario());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setEnable(false);
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
}
