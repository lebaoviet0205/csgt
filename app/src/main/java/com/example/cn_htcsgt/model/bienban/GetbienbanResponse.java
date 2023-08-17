package com.example.cn_htcsgt.model.bienban;

import androidx.annotation.NonNull;

import com.example.cn_htcsgt.model.BaseResponse;

import java.util.ArrayList;

public class GetbienbanResponse extends BaseResponse {
    public ArrayList<GetbienbanResponse.Data> data;
    public class Data{

        public String iD_BB;
        public String maPhuong;
        public String maTaiKhoan;
        public String maXe;
        public String maNguoiViPham;
        public String maLoi;
        public String maBienBan;
        public String ngayLap;
        public String anhBienBan;
        public String trangThai;
        public String bienSoXe;
        public String noiDungViPham;
        public String yKienNguoiViPham;
        public String idphuong;
        @NonNull
        @Override
        public String toString() {
            return iD_BB;
        }
    }
}
