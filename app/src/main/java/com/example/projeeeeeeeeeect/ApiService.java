package com.example.projeeeeeeeeeect;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;
import java.util.List;

class UserLoginRequest { String email; String password;
    public UserLoginRequest(String email, String password){
        this.email = email;
        this.password = password;
    }
}
class UserLoginResponse{ String token; String message; User user;
    public UserLoginResponse(String token, String message, int role_id){
        this.token = token;
        this.message = message;
    }
    public User getUser(){
        return user;
    }
    public String getToken() {
        return token;
    }
}

class User {
    int role_id;
    public int getRoleId(){
        return role_id;
    }
}

public interface ApiService {
    @POST("api/auth/login")
    Call<UserLoginResponse> loginUser(@Body UserLoginRequest loginRequest);
}
