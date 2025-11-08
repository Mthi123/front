package com.example.projeeeeeeeeeect;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class login extends AppCompatActivity {

    EditText emailInput, passwordInput;
    Spinner roleSpinner;
    Button loginButton, anonymousButton;
    TextView signupLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        roleSpinner = findViewById(R.id.roleSpinner);
        loginButton = findViewById(R.id.loginButton);
        anonymousButton = findViewById(R.id.anonymousButton);
        signupLink = findViewById(R.id.signupLink);

        // Handle login button click
        loginButton.setOnClickListener(v -> {
            String role = roleSpinner.getSelectedItem().toString();
            switch (role) {
                case "Admin":
                    // TODO: Uncomment once AdminDashboardActivity is created
                    // startActivity(new Intent(this, AdminDashboardActivity.class));
                    Toast.makeText(this, "Admin dashboard coming soon.", Toast.LENGTH_SHORT).show();
                    break;
                case "Counselor":
                    // TODO: Uncomment once CounselorDashboardActivity is created
                    startActivity(new Intent(this, CounsilorDashboard.class));
                    break;
                case "NGO":
                    // TODO: Uncomment once NGODashboardActivity is created
                    // startActivity(new Intent(this, NGODashboardActivity.class));
                    Toast.makeText(this, "NGO dashboard coming soon.", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    // Only MainActivity exists for now
                    startActivity(new Intent(this, MainActivity.class));
                    break;
            }
        });


        // Anonymous login
        anonymousButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("anonymous", true);
            startActivity(intent);
        });

        // Go to sign-up
        signupLink.setOnClickListener(v -> {
            startActivity(new Intent(this, SignUp.class));
        });
    }
}

