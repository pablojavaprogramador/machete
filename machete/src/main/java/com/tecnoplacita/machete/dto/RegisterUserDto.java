package com.tecnoplacita.machete.dto;

public class RegisterUserDto {

    private String email;
    private String password;
    private String usuario;
    private boolean aceptoAvisoPrivacidad; // Nuevo campo

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAceptoAvisoPrivacidad() {
        return aceptoAvisoPrivacidad;
    }

    public void setAceptoAvisoPrivacidad(boolean aceptoAvisoPrivacidad) {
        this.aceptoAvisoPrivacidad = aceptoAvisoPrivacidad;
    }
}
