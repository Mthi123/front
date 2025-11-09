package com.example.projeeeeeeeeeect.Models;

public class User {
    private int role_id;
    private int id;
    private String name;
    private String email;

    // CRITICAL: The getter method fixes the login crash
    public int getRole_id() { return role_id; }
    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}