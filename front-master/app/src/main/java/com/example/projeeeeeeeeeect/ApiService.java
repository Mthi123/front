package com.example.projeeeeeeeeeect;

// Import 'List' from java.util
import java.util.List;

// Import Gson annotation (You'll need this!)
import com.example.projeeeeeeeeeect.Models.PublishArticleRequest;
import com.example.projeeeeeeeeeect.Models.PublishArticleResponse;
import com.example.projeeeeeeeeeect.Models.Report;
import com.example.projeeeeeeeeeect.Models.SubmitReportRequest;
import com.example.projeeeeeeeeeect.Models.SubmitReportResponse;
import com.google.gson.annotations.SerializedName;

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
}


// --- AUTH DATA CLASSES (No changes here) ---

class UserLoginRequest {
    String email;
    String password;
    public UserLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

class UserLoginResponse {
    String token;
    String message;
    User user;
    public User getUser() { return user; }
    public String getToken() { return token; }
}

class User {
    int role_id;
    int id;
    String name;
    String email;
}

class CreateUserRequest {
    String name;
    String email;
    String phone;
    String role;
    String password;
    public CreateUserRequest(String name, String email, String phone, String role, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.password = password;
    }
}

class CreateUserResponse {
    String message;
    String id;
    String name;
    String email;
}


// --- (NEW!) ACCURATE REPORT DATA CLASSES ---

/**
 * 2. Represents a statistic for "reports by type"
 * (Matches what you get from /api/reports/type)
 */
class ReportTypeStat {
    @SerializedName("incident_type_id")
    int incidentTypeId;

    @SerializedName("incident_type")
    String incidentType;

    @SerializedName("count")
    int count;

    // These keys with a "." in them are unusual,
    // but @SerializedName can handle them!
    @SerializedName("IncidentType.id")
    int nestedId;

    @SerializedName("IncidentType.name")
    String nestedName;
}

/**
 * 3. Represents a statistic for "reports by status"
 * (Matches what you get from /api/reports/status)
 */
class ReportStatusStat {
    @SerializedName("status_id")
    int statusId;

    @SerializedName("status_name")
    String statusName;

    @SerializedName("count")
    int count;

    @SerializedName("StatusType.id")
    int nestedId;

    @SerializedName("StatusType.name")
    String nestedName;
}