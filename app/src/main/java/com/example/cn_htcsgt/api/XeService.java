package com.example.cn_htcsgt.api;

import com.example.cn_htcsgt.model.xe.GetResponse;
import com.example.cn_htcsgt.model.xe.GetXeResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.QueryMap;

public interface XeService {
    @GET("/api/XE/Index")
    Call<GetResponse> XeIndex(@Header("Authorization") String auth);
    @GET("/api/XE/GetXE")
    Call<GetXeResponse> XeGetXe(@QueryMap Map<String, String> options, @Header("Authorization") String auth);
}
