package com.example.projeeeeeeeeeect;

// Import 'List' from java.util
import java.util.List;

// Import Gson annotation (You'll need this!)
import com.example.projeeeeeeeeeect.Models.Article;
import com.example.projeeeeeeeeeect.Models.CreateUserRequest;
import com.example.projeeeeeeeeeect.Models.CreateUserResponse;
import com.example.projeeeeeeeeeect.Models.PublishArticleRequest;
import com.example.projeeeeeeeeeect.Models.PublishArticleResponse;
import com.example.projeeeeeeeeeect.Models.Report;
import com.example.projeeeeeeeeeect.Models.ReportStatusStat;
import com.example.projeeeeeeeeeect.Models.ReportTypeStat;
import com.example.projeeeeeeeeeect.Models.SubmitReportRequest;
import com.example.projeeeeeeeeeect.Models.SubmitReportResponse;
import com.example.projeeeeeeeeeect.Models.UserLoginRequest;
import com.example.projeeeeeeeeeect.Models.UserLoginResponse;

// Import Retrofit annotations
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

// --- Your API Interface (This part was already correct) ---
public interface ApiService {

    // --- AUTH ---
    @POST("api/auth/login")
    Call<UserLoginResponse> loginUser(@Body UserLoginRequest loginRequest);

    @POST("api/roles/admin")
    Call<CreateUserResponse> createUser(@Body CreateUserRequest createUserRequest);


    // --- REPORTS ---

    // 1. View all reports
    @GET("api/reports")
    Call<List<Report>> getAllReports();

    // 2. View reports by type
    @GET("api/reports/type")
    Call<List<ReportTypeStat>> getReportsByType();

    // 3. View incident types by location
    @GET("api/reports/incident-types/location/{location}")
    Call<List<ReportTypeStat>> getReportsByLocation(@Path("location") String location);

    // 4. View reports by status
    @GET("api/reports/status")
    Call<List<ReportStatusStat>> getReportsByStatus();

    @POST("api/resources") // <-- NEW ENDPOINT
    Call<PublishArticleResponse> publishResource(@Body PublishArticleRequest request);

    @POST("api/reports") // <-- NEW ENDPOINT: SUBMIT A REPORT
    Call<SubmitReportResponse> submitReport(@Body SubmitReportRequest request);

    @GET("api/articles")
    Call<List<Article>> getArticles();
}


// --- AUTH DATA CLASSES (No changes here) ---


// --- (NEW!) ACCURATE REPORT DATA CLASSES ---

