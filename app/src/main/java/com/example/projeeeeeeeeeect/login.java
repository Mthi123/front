package com.example.projeeeeeeeeeect;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

// ADD THESE IMPORTS FOR RETROFIT
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        // --- THIS IS THE NEW CLICK LISTENER ---
        loginButton.setOnClickListener(v -> {
            // Get the text from the fields
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString();
            String role = roleSpinner.getSelectedItem().toString();

            // 1. Create the request object
            UserLoginRequest loginRequest = new UserLoginRequest(email, password);

            // 2. Get the ApiService from your RetrofitClient
            ApiService apiService = RetrofitClient.getApiService();

            // 3. Make the network call
            Call<UserLoginResponse> call = apiService.loginUser(loginRequest);

            // 4. Handle the response (asynchronously)
            call.enqueue(new Callback<UserLoginResponse>() {
                @Override
                public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
                    // Check if the API call was successful (HTTP 200-299)
                    if (response.isSuccessful() && response.body() != null) {
                        // Login Success!
                        // You can get data from the response, e.g., response.body().token
                        Toast.makeText(login.this, "Login Successful!", Toast.LENGTH_SHORT).show();

                        // Now, navigate based on the selected role
                        switch (role) {
                            case "Admin":
                                startActivity(new Intent(login.this, AdminDashboardActivity.class));
                                break;
                            case "Counselor":
                                startActivity(new Intent(login.this, CounsilorDashboard.class));
                                break;
                            case "NGO":
                                Toast.makeText(login.this, "NGO dashboard coming soon.", Toast.LENGTH_SHORT).show();
                                break;
                            default: // "User"
                                startActivity(new Intent(login.this, MainActivity.class));
                                break;
                        }
                        finish(); // Close the login activity

                    } else {
                        // API call was not successful (e.g., 401 Unauthorized, 404 Not Found)
                        Toast.makeText(login.this, "Invalid email or password.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                    // This happens on network errors (e.g., no internet, server is down)
                    Toast.makeText(login.this, "Network Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });


        // Anonymous login (no change needed)
        anonymousButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("isAnonymous", true); // This now matches MainActivity
            startActivity(intent);
        });

        // Go to sign-up (no change needed)
        signupLink.setOnClickListener(v -> {
            startActivity(new Intent(this, SignUp.class));
        });
    }
}