package com.example.cn_htcsgt.model.Huyen;

public class GetHuyenRequest {
    public class Codebeautify {
        private String id_huyen;
        private String tenhuyen;
        private String id_tinh;


        // Getter Methods

        public String getId_huyen() {
            return id_huyen;
        }

        public String getTenhuyen() {
            return tenhuyen;
        }

        public String getId_tinh() {
            return id_tinh;
        }

        // Setter Methods

        public void setId_huyen(String id_huyen) {
            this.id_huyen = id_huyen;
        }

        public void setTenhuyen(String tenhuyen) {
            this.tenhuyen = tenhuyen;
        }

        public void setId_tinh(String id_tinh) {
            this.id_tinh = id_tinh;
        }
    }
}
