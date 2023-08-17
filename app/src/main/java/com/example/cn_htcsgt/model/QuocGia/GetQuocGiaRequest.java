package com.example.cn_htcsgt.model.QuocGia;

public class GetQuocGiaRequest {
    public class Codebeautify {
        private String loaiQuocGia;
        private String tenGoiChung;
        private String tenChinhThuc;
        private String maDienThoai;


        // Getter Methods

        public String getLoaiQuocGia() {
            return loaiQuocGia;
        }

        public String getTenGoiChung() {
            return tenGoiChung;
        }

        public String getTenChinhThuc() {
            return tenChinhThuc;
        }

        public String getMaDienThoai() {
            return maDienThoai;
        }

        // Setter Methods

        public void setLoaiQuocGia(String loaiQuocGia) {
            this.loaiQuocGia = loaiQuocGia;
        }

        public void setTenGoiChung(String tenGoiChung) {
            this.tenGoiChung = tenGoiChung;
        }

        public void setTenChinhThuc(String tenChinhThuc) {
            this.tenChinhThuc = tenChinhThuc;
        }

        public void setMaDienThoai(String maDienThoai) {
            this.maDienThoai = maDienThoai;
        }
    }
}
