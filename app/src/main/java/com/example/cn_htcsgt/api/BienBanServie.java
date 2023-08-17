package com.example.cn_htcsgt.api;

import com.example.cn_htcsgt.model.bienban.GetTTBBRespnse;
import com.example.cn_htcsgt.model.bienban.GetbienbanRequest;
import com.example.cn_htcsgt.model.bienban.GetbienbanResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface BienBanServie {
    @GET("/api/BienBan/Index")
    Call<GetTTBBRespnse> GetBienBanIndex(@Header("Authorization") String auth);
    @POST("/api/BienBan/ThemBienBan")
    Call<GetbienbanResponse> getBienBan(@Header("Authorization") String auth,@Body GetbienbanRequest body);
}
