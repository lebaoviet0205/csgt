package com.example.cn_htcsgt.api;

import com.example.cn_htcsgt.model.Phuong.GetPhuongResonse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface PhuongServie {
    @GET("/api/Phuong/GetPhuong")
    Call<GetPhuongResonse> GetPhuong(@Header("Authorization") String auth, @Query("id_huyen")  String id_huyen, @Query("tenphuong")  String tenphuong  );
}
