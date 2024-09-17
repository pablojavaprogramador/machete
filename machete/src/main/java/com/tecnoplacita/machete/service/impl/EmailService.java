package com.tecnoplacita.machete.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarCorreoVerificacion(String destinatario, String token) {
        String asunto = "Verificación de Cuenta";
        String cuerpo = "Por favor, haz clic en el siguiente enlace para verificar tu cuenta: "
                        + "http://localhost:9090/verificar?token=" + token;

        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(destinatario);
        mensaje.setSubject(asunto);
        mensaje.setText(cuerpo);
        mensaje.setFrom("tecnoplacita@gmail.com");

        mailSender.send(mensaje);
    }
}
