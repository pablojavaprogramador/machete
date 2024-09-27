package com.tecnoplacita.machete.dto;

import java.util.Date;

public class NotificationDTO {
    private Long id;
    private String message;
    private UserDTO user;
    private String type;
    private Date createdAt;
    private boolean isRead;

    // Constructor
    public NotificationDTO(Long id, String message, UserDTO user, String type, Date createdAt, boolean isRead) {
        this.id = id;
        this.message = message;
        this.user = user;
        this.type = type;
        this.createdAt = createdAt;
        this.isRead = isRead;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
