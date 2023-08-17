package com.example.cn_htcsgt.model.QuocGia;
import androidx.annotation.NonNull;

import com.example.cn_htcsgt.model.BaseResponse;

import java.util.ArrayList;

public class GetQuocGiaResponse extends BaseResponse {
    public ArrayList<Data> data;
    public class Data{
        public String loaiQuocGia;
        public String tenGoiChung;
        public String tenChinhThuc;
        public String maDienThoai;
        public String iD_quocgia;

        @NonNull
        @Override
        public String toString() {
            return tenGoiChung;
        }
    }

}
