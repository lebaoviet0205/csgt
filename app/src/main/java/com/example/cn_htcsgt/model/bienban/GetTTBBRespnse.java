package com.example.cn_htcsgt.model.bienban;

import androidx.annotation.NonNull;

import com.example.cn_htcsgt.model.BaseResponse;

import java.util.ArrayList;

public class GetTTBBRespnse extends BaseResponse {
    public ArrayList<GetTTBBRespnse.Data> data;
    public class Data{
        public String maTaikhoan;
        public String iD_BB;
        public String ngaylap;
        public String nguoiviphon;
        public String nguoiViPham;
        public String phuong;
        public String huyen;
        public String tinh;
        public String quocgia;
        public String xe;
        public String loi;
        public String noidungloi;
        public String mucphat;
        @NonNull
        @Override
        public String toString() {
            return iD_BB;
        }
    }
}
