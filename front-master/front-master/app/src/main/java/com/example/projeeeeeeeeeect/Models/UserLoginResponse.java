package com.example.projeeeeeeeeeect.Models;

public class UserLoginResponse {
    private String token;
    private String message;
    private User user;

    public User getUser() { return user; }
    public String getToken() { return token; }
    public String getMessage() { return message; }
}