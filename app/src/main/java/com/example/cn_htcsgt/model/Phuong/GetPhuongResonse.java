package com.example.cn_htcsgt.model.Phuong;

import androidx.annotation.NonNull;

import com.example.cn_htcsgt.model.BaseResponse;

import java.util.ArrayList;

public class GetPhuongResonse extends BaseResponse {
    public ArrayList<GetPhuongResonse.Data> data;
    public class Data{
        public String iD_phuong;
        public String tenphuong;
        public String id_huyen;

        @NonNull
        @Override
        public String toString() {
            return tenphuong;
        }
    }
}
