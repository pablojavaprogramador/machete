package com.tecnoplacita.machete.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<String> requestPasswordReset(@RequestBody PasswordResetRequestDto request) {
        boolean sent = authenticationService.sendPasswordResetEmail(request.getEmail());

        if (sent) {
            return ResponseEntity.ok("Se ha enviado un correo para restablecer la contraseña.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Correo electrónico no encontrado.");
        }
    }

    @PostMapping("/confirm-reset")
    public ResponseEntity<String> confirmPasswordReset(@RequestBody PasswordResetConfirmationDto request) {
        boolean successful = authenticationService.resetPassword(request.getToken(), request.getNewPassword());

        if (successful) {
            return ResponseEntity.ok("Contraseña restablecida correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token inválido o expirado.");
        }
    }
}