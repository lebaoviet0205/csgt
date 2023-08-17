package com.example.cn_htcsgt.model.user;

import androidx.annotation.NonNull;

import com.example.cn_htcsgt.model.BaseResponse;

public class GetUserResponse extends BaseResponse {
    public GetUserResponse.Data data;
    public class Data{
        public String id;

        @NonNull
        @Override

        public String toString() {
            return id;
        }
    }
}

