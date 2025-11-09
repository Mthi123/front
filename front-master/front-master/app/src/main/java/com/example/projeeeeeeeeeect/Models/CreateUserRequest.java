package com.example.projeeeeeeeeeect.Models;

public class CreateUserRequest {
    private String name;
    private String email;
    private String phone;
    private String role;
    private String password;

    public CreateUserRequest(String name, String email, String phone, String role, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.password = password;
    }
}