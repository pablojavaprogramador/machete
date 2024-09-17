package com.tecnoplacita.machete.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tecnoplacita.machete.model.TokenVerificacion;
import com.tecnoplacita.machete.model.User;
import com.tecnoplacita.machete.repository.TokenVerificacionRepository;
import com.tecnoplacita.machete.repository.UserRepository;

@RestController
public class VerificacionController {

    @Autowired
    private TokenVerificacionRepository tokenVerificacionRepository;

    @Autowired
    private UserRepository usuarioRepository;

    @GetMapping("/verificar")
    public ResponseEntity<String> verificarCuenta(@RequestParam("token") String token) {
        TokenVerificacion tokenVerificacion = tokenVerificacionRepository.findByToken(token);

        if (tokenVerificacion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Token inválido");
        }

        // Verificar si el token ha expirado
        if (tokenVerificacion.getFechaExpiracion().isBefore(LocalDateTime.now())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token expirado");
        }

        // Habilitar la cuenta del usuario
        User usuario = tokenVerificacion.getUsuario();
        usuario.setEnable(true);
        usuarioRepository.save(usuario);

        return ResponseEntity.ok("Cuenta verificada correctamente");
    }
}

