package com.example.cn_htcsgt.api;

import com.example.cn_htcsgt.model.QuocGia.GetQuocGiaResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface QuocGiaService {
    @GET("/api/QuocGia/Index")
    Call<GetQuocGiaResponse> GetQuocGia(@Header("Authorization") String auth);
}
