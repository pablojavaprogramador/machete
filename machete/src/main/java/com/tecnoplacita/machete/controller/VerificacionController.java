package com.tecnoplacita.machete.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tecnoplacita.machete.dto.ApiResponse;
import com.tecnoplacita.machete.dto.ValidacionEmailRequestDto;
import com.tecnoplacita.machete.exceptions.TokenInvalidException;
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

    @PostMapping("/verificar")
    public ResponseEntity<ApiResponse> verificarCuenta(@RequestBody ValidacionEmailRequestDto token) throws TokenInvalidException {
        TokenVerificacion tokenVerificacion = tokenVerificacionRepository.findByToken(token.getToken().toString());

        if (tokenVerificacion == null) {
            throw new TokenInvalidException("Token inválido");
        }

        // Verificar si el token ha expirado
        if (tokenVerificacion.getFechaExpiracion().isBefore(LocalDateTime.now())) {
            throw new TokenInvalidException("Token expirado");
        }

        // Habilitar la cuenta del usuario
        User usuario = tokenVerificacion.getUsuario();
        usuario.setHabilitado(true);
        usuarioRepository.save(usuario);

        ApiResponse respuesta = new ApiResponse(true, "Cuenta verificada correctamente");
        return ResponseEntity.ok(respuesta);
    }

}

