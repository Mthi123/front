package com.example.projeeeeeeeeeect;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;
import java.util.List;

class UserLoginRequest { String email; String password; }
class UserLoginResponse{ String token; String message; }

public interface ApiService {
    @POST("api/auth/login")
    Call<UserLoginResponse> loginUser(@Body UserLoginRequest loginRequest);
}
