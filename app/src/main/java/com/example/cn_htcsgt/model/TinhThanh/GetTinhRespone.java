package com.example.cn_htcsgt.model.TinhThanh;

import androidx.annotation.NonNull;

import com.example.cn_htcsgt.model.BaseResponse;

import java.util.ArrayList;

public class GetTinhRespone extends BaseResponse {
    public ArrayList<GetTinhRespone.Data> data;
    public class Data{
        public String id_tinh;
        public String id_quocgia;
        public String tentinhthanh;


        @NonNull
        @Override
        public String toString() {
            return tentinhthanh;
        }
    }
}
