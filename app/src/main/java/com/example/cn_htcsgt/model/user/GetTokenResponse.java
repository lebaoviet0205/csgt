package com.example.cn_htcsgt.model.user;

import androidx.annotation.NonNull;

import com.example.cn_htcsgt.model.BaseResponse;

public class GetTokenResponse extends BaseResponse {
    private String data;


    public String getData() { return data; }
    public void setData(String value) { this.data = value; }


}
