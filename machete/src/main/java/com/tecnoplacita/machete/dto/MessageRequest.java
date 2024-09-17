package com.tecnoplacita.machete.dto;

public class MessageRequest {
    private String sender;
    private String receiver;
    private String message;

    // Getters and Setters
    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }
    public String getReceiver() { return receiver; }
    public void setReceiver(String receiver) { this.receiver = receiver; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
