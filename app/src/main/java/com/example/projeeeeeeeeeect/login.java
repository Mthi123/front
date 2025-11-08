package com.example.projeeeeeeeeeect;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends AppCompatActivity {

    EditText emailInput, passwordInput;
    Button loginButton, anonymousButton;
    TextView signupLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginButton);
        anonymousButton = findViewById(R.id.anonymousButton);
        signupLink = findViewById(R.id.signupLink);

        // --- THIS IS THE NEW CLICK LISTENER ---
        loginButton.setOnClickListener(v -> {
            // Get the text from the fields
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString();

            // 1. Create the request object (no change here)
            UserLoginRequest loginRequest = new UserLoginRequest(email, password);

            // 2. Get the ApiService (no change here)
            ApiService apiService = RetrofitClient.getApiService();

            // 3. Make the network call (no change here)
            Call<UserLoginResponse> call = apiService.loginUser(loginRequest);

            // 4. Handle the response (THIS IS THE UPDATED PART)
            call.enqueue(new Callback<UserLoginResponse>() {
                @Override
                public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {

                    if (response.isSuccessful() && response.body() != null) {
                        // Login Success!
                        Toast.makeText(login.this, "Login Successful!", Toast.LENGTH_SHORT).show();

                        // Get the roleId from the response
                        int roleId = response.body().getRoleId();

                        // 3=Admin, 2=Counselor, 1=User (or default)
                        switch (roleId) {
                            case 3:
                                startActivity(new Intent(login.this, AdminDashboardActivity.class));
                                break;
                            case 2: // Assuming 2 is for Counselor
                                startActivity(new Intent(login.this, CounsilorDashboard.class));
                                break;
                            case 1: // Assuming 1 is for User
                            default:
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
                    // This happens on network errors
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