package com.example.cn_htcsgt.model.Huyen;

import androidx.annotation.NonNull;

import com.example.cn_htcsgt.model.BaseResponse;

import java.util.ArrayList;

public class GetHuyenResPonse extends BaseResponse {
    public ArrayList<GetHuyenResPonse.Data> data;
    public class Data{
        public String id_huyen;
        public String tenhuyen;
        public String id_tinh;

        @NonNull
        @Override

        public String toString() {
            return tenhuyen;
        }
    }
}
