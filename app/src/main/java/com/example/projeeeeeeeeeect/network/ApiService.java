package com.example.projeeeeeeeeeect.network;

import java.util.List;

// Import all your models with a wildcard
import com.example.projeeeeeeeeeect.Models.*;

// Import Retrofit annotations
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    // --- AUTH ---
    @POST("api/auth/login")
    Call<UserLoginResponse> loginUser(@Body UserLoginRequest loginRequest);

    @POST("api/roles/admin")
    Call<CreateUserResponse> createUser(@Body CreateUserRequest createUserRequest);


    // --- REPORTS ---
    @GET("api/reports")
    Call<List<Report>> getAllReports();

    @GET("api/reports/type")
    Call<List<ReportTypeStat>> getReportsByType();

    @GET("api/reports/incident-types/location/{location}")
    Call<List<ReportTypeStat>> getReportsByLocation(@Path("location") String location);

    @GET("api/reports/status")
    Call<List<ReportStatusStat>> getReportsByStatus();


    // --- ARTICLES ---
    @GET("api/articles") // Assumed endpoint, change if needed
    Call<List<Resource>> getAllArticles();


    // --- CHAT ---
    @POST("api/chat/start")
    Call<ChatStartResponse> startChat(@Body ChatStartRequest chatStartRequest);

    @POST("api/chat/send")
    Call<SendMessageResponse> sendMessage(@Body SendMessageRequest sendMessageRequest);

}


