package com.example.cn_htcsgt.base;


import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface IApiService {
    @POST
    Call<ResponseBody> post(@Url String url, @Body Object request , @HeaderMap Map<String, String> headers);
    @GET
    Call<ResponseBody> get(@Url String url, @QueryMap Map<String, String> params, @HeaderMap  Map<String, String> headers);
}
