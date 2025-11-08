package com.example.projeeeeeeeeeect;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminDashboardActivity extends AppCompatActivity {

    private Button viewUsersBtn, viewReportsBtn, manageCounselorsBtn, viewResourcesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        viewUsersBtn = findViewById(R.id.viewUsersBtn);
        viewReportsBtn = findViewById(R.id.viewReportsBtn);
        manageCounselorsBtn = findViewById(R.id.manageCounselorsBtn);
        viewResourcesBtn = findViewById(R.id.viewResourcesBtn);

        // TODO: Implement actual logic for each button
        viewUsersBtn.setOnClickListener(v -> {
            // Open activity to view all users
            Intent intent = new Intent(this, AdminViewUsersActivity.class);
            startActivity(intent);
        });

        viewReportsBtn.setOnClickListener(v -> {
            // Open activity to view all reports
            Intent intent = new Intent(this, AdminViewReportsActivity.class);
            startActivity(intent);
        });

        manageCounselorsBtn.setOnClickListener(v -> {
            // Open activity to manage counselors/NGOs
            Intent intent = new Intent(this, AdminManageCounselorsActivity.class);
            startActivity(intent);
        });

        viewResourcesBtn.setOnClickListener(v -> {
            // Open activity to view all resources
            Intent intent = new Intent(this, Resources.class); // reusing user resource view
            startActivity(intent);
        });
    }
}
