package com.example.projeeeeeeeeeect.Models;

import com.google.gson.annotations.SerializedName;

// Minimal response model for success confirmation
public class PublishArticleResponse {

    // Fields made private
    private String message;

    @SerializedName("id")
    private int id;

    // Getter for message
    public String getMessage() {
        return message;
    }

    // Getter for id (Required for best practice)
    public int getId() {
        return id;
    }
}