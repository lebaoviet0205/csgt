package com.example.cn_htcsgt.model.xe;

import com.example.cn_htcsgt.model.BaseResponse;

import java.util.ArrayList;

public class GetXeResponse extends BaseResponse {
    public Data data;

    public class Data {
        public String iD_XE;
        public String bks;
        public String seriNo;
        public String tenchuxe;
        public String diaChi;
        public String hangxe;
        public String loaixe;
        public String mauxe;
        public String engineNo;
        public String chasssisNo;
        public String ngayMua;
        public String ngayhethan;
        public ArrayList<Object> Xes;
        private String SearchText;

        public boolean SeachText(String text) {
            String textSearch = convert(text).toUpperCase();
            if (SearchText == null || SearchText.isEmpty()) SearchText = convert(bks).toUpperCase();
            return SearchText.contains(textSearch);
        }

        public String convert(String str) {

            String PaseString1 = str.replaceAll("[-\\\\+\\\\.\\\\^:,\\\\ \n \t]", "");


            return PaseString1;
        }
    }
}

