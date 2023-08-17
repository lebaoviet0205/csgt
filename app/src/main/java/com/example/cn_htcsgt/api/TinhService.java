package com.example.cn_htcsgt.api;

import com.example.cn_htcsgt.model.TinhThanh.GetTinhRespone;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface TinhService {
    @GET("/api/Tinhthanh/Index")
    Call<GetTinhRespone> GetTinh(@Header("Authorization") String auth, @Query("ID_quocgia")  String ID_quocgia, @Query("tentinhthanh")  String tentinhthanh);
}
