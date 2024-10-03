package com.tecnoplacita.machete.dto;



public class MessageRequestGpt {
    private String sender;
    private String receiver;
    private String message;
    private String topic; // Tema del mensaje
    private RequestType requestType; // Tipo de solicitud
    private String level; // Nivel de dificultad (opcional)

    // Constructor
    public MessageRequestGpt(String sender, String receiver, String message, String topic, RequestType requestType, String level) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.topic = topic;
        this.requestType = requestType;
        this.level = level;
    }

    // Getters y Setters
    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }

    public String getReceiver() { return receiver; }
    public void setReceiver(String receiver) { this.receiver = receiver; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }

    public RequestType getRequestType() { return requestType; }
    public void setRequestType(RequestType requestType) { this.requestType = requestType; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }
}
