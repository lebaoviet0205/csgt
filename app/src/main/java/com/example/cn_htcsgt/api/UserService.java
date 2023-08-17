package com.example.cn_htcsgt.api;

import com.example.cn_htcsgt.model.user.GetTokenRequest;
import com.example.cn_htcsgt.model.user.GetTokenResponse;
import com.example.cn_htcsgt.model.user.GetUserResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface UserService {
    @POST("/api/Auth/Validate/Login")
    Call<GetTokenResponse> getToken(@Body GetTokenRequest body);
    @GET("/api/NguoiDung/Index")
    Call<GetUserResponse> GetIDUser(@QueryMap Map<String, String> options, @Header("Authorization") String auth);}

