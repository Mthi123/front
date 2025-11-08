package com.example.projeeeeeeeeeect.network;

import android.content.Context; // Import Context

import com.example.projeeeeeeeeeect.auth.SessionManager; // Import SessionManager

import okhttp3.OkHttpClient; // Import OkHttpClient
import okhttp3.Request; // Import Request
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "http://10.0.2.2:3000/";
    private static Retrofit retrofit = null;
    private static SessionManager sessionManager;

    /**
     * This method creates a single instance of Retrofit and the ApiService.
     * We need Context now to initialize the SessionManager.
     */
    public static ApiService getApiService(Context context) {
        // Initialize SessionManager once
        if (sessionManager == null) {
            sessionManager = new SessionManager(context);
        }

        if (retrofit == null) {
            // Create an OkHttpClient to add the auth header
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            httpClient.addInterceptor(chain -> {
                // Get the token from our SessionManager
                String token = sessionManager.getAuthToken();
                Request original = chain.request();

                // If token exists, add it to the header
                if (token != null) {
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", "Bearer " + token) // Standard auth header
                            .method(original.method(), original.body());
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }

                // If no token, proceed with the original request
                return chain.proceed(original);
            });

            OkHttpClient client = httpClient.build();

            // Build Retrofit with the custom OkHttp client
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client) // Set the custom client
                    .build();
        }
        return retrofit.create(ApiService.class);
    }
}