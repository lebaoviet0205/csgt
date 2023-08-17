package com.example.cn_htcsgt.api;

import com.example.cn_htcsgt.model.TTCD.GetTTCDResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.QueryMap;

public interface TTCDService {
    @GET("/api/TTCD/Index")
    Call<GetTTCDResponse> GetTTCDIndex(@Header("Authorization") String auth);
    @GET("/api/TTCD/GetCongDan")
    Call<GetTTCDResponse> GetCongDan(@QueryMap Map<String, String> options, @Header("Authorization") String auth);}

