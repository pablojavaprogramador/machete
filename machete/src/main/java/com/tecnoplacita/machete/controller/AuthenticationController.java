package com.tecnoplacita.machete.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecnoplacita.machete.dto.ApiResponse;
import com.tecnoplacita.machete.dto.LoginResponse;
import com.tecnoplacita.machete.dto.LoginUserDto;
import com.tecnoplacita.machete.dto.PasswordResetConfirmationDto;
import com.tecnoplacita.machete.dto.PasswordResetRequestDto;
import com.tecnoplacita.machete.dto.RegisterUserDto;
import com.tecnoplacita.machete.model.User;
import com.tecnoplacita.machete.service.JwtService;
import com.tecnoplacita.machete.service.impl.AuthenticationService;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
    	
    	System.out.println(registerUserDto.isAceptoAvisoPrivacidad()+ registerUserDto.getEmail()+registerUserDto.getPassword()+registerUserDto.getUsuario());
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
    
    
    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse> requestPasswordReset(@RequestBody PasswordResetRequestDto request) {
        // Inicializar la respuesta
        ApiResponse respuesta = new ApiResponse(false, null);
        
        // Enviar el correo para restablecer la contraseña
        boolean sent = authenticationService.sendPasswordResetEmail(request.getEmail());

        if (sent) {
            // Configurar respuesta exitosa
            respuesta.setSuccess(true);
            respuesta.setMessage("Se ha enviado un correo para restablecer la contraseña.");
            return ResponseEntity.ok(respuesta);
        } else {
            // Configurar respuesta fallida
            respuesta.setSuccess(false);
            respuesta.setMessage("Correo electrónico no encontrado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }


    @PostMapping("/confirm-reset")
    public ResponseEntity<ApiResponse> confirmPasswordReset(@RequestBody PasswordResetConfirmationDto request) {
        boolean successful = authenticationService.resetPassword(request.getToken(), request.getNewPassword());
        ApiResponse respuesta = new ApiResponse(false, null);
        if (successful) {
           
            respuesta.setSuccess(true);
            respuesta.setMessage("Contraseña restablecida correctamente.");
            return ResponseEntity.ok(respuesta);
        } else {
           
            respuesta.setSuccess(false);
            respuesta.setMessage("Token inválido o expirado.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
            
        }
    }
}