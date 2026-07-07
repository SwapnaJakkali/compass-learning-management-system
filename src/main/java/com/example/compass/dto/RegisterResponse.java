package com.example.compass.dto;

public class RegisterResponse {
    private String message;
    private String email;

    public RegisterResponse(String message, String email) {
        this.message = message;
        this.email = email;
    }

    // Getters and Setters
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}