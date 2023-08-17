package com.example.cn_htcsgt.api;

import com.example.cn_htcsgt.model.Huyen.GetHuyenResPonse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface HuyenService {
    @GET("/api/Huyen/Gethuyen")
    Call<GetHuyenResPonse> GetHuyen(@Header("Authorization") String auth, @Query("ID_TinhThanhPho")  String ID_TinhThanhPho, @Query("tenhuyen")  String tenhuyen );
}
