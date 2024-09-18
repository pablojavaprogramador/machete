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
    public ResponseEntity<ApiResponse> verificarCuenta(@RequestBody ValidacionEmailRequestDto token) {
        TokenVerificacion tokenVerificacion = tokenVerificacionRepository.findByToken(token.getToken().toString());

        ApiResponse respuesta=new ApiResponse(false, null);
        if (tokenVerificacion == null) {
         
            respuesta.setSuccess(false);
            respuesta.setMessage("Token inválido");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }

        // Verificar si el token ha expirado
        if (tokenVerificacion.getFechaExpiracion().isBefore(LocalDateTime.now())) {
        
            respuesta.setSuccess(false);
            respuesta.setMessage("Token expirado");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }

        // Habilitar la cuenta del usuario
        User usuario = tokenVerificacion.getUsuario();
        usuario.setHabilitado(true);
        usuarioRepository.save(usuario);

        respuesta.setSuccess(true);
        respuesta.setMessage("Cuenta verificada correctamente");
        return ResponseEntity.ok(respuesta);
        
      
    }
}

