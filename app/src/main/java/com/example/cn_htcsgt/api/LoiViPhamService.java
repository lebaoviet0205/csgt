package com.example.cn_htcsgt.api;

import com.example.cn_htcsgt.model.LoiViPham.GetLoiViPhamResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface LoiViPhamService {
    @GET("/api/LoiVP/Index")
    Call<GetLoiViPhamResponse> GetLoiViPhamIndex(@Header("Authorization") String auth);}
