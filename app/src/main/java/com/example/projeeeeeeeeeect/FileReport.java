package com.example.projeeeeeeeeeect;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FileReport extends AppCompatActivity {

    private EditText etIncidentType, etLocation, etDescription, etDate;
    private Button btnSubmitReport, btnCancelReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_report);

        // Bind views
        etIncidentType = findViewById(R.id.etIncidentType);
        etLocation = findViewById(R.id.etLocation);
        etDescription = findViewById(R.id.etDescription);
        etDate = findViewById(R.id.etDate);

        btnSubmitReport = findViewById(R.id.btnSubmitReport);
        btnCancelReport = findViewById(R.id.btnCancelReport);

        // Submit button logic
        btnSubmitReport.setOnClickListener(v -> submitReport());

        // Cancel button logic
        btnCancelReport.setOnClickListener(v -> finish()); // simply closes this screen
    }

    private void submitReport() {
        String type = etIncidentType.getText().toString().trim();
        String location = etLocation.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        String date = etDate.getText().toString().trim();

        // Validate required fields
        if (TextUtils.isEmpty(type) || TextUtils.isEmpty(location) || TextUtils.isEmpty(description)) {
            Toast.makeText(this, "Please fill in all required fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO: Save report to Firebase or local database

        // Show confirmation
        Toast.makeText(this, "Report submitted successfully.", Toast.LENGTH_LONG).show();

        // Close the form and return to dashboard
        finish();
    }
}