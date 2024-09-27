package com.tecnoplacita.machete.dto;

public class UserDTO {
    private Integer id;
    private String usuario;
    private String email;

    // Constructor
    public UserDTO(Integer id, String usuario, String email) {
        this.id = id;
        this.usuario = usuario;
        this.email = email;
    }

 

	// Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
}
