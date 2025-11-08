package com.example.projeeeeeeeeeect;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CounsilorDashboard extends AppCompatActivity {

    private Button viewReportsBtn, chatWithUsersBtn, publishResourceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counsilor_dashboard);

        viewReportsBtn = findViewById(R.id.viewReportsBtn);
        chatWithUsersBtn = findViewById(R.id.chatWithUsersBtn);
        publishResourceBtn = findViewById(R.id.publishResourceBtn);

        // View reports submitted by users
        viewReportsBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, CounsilorViewReports.class);
            startActivity(intent);
        });

        // Chat with users (shared ConversationActivity)
        chatWithUsersBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, Conversation.class);
            intent.putExtra("role", "counselor"); // Pass role so ConversationActivity knows sender type
            startActivity(intent);
        });

        // Publish resources/articles for users
        publishResourceBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, PublishResource.class);
            startActivity(intent);
        });
    }
}