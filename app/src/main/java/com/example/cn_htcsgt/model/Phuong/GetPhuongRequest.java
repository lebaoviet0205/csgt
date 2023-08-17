package com.example.cn_htcsgt.model.Phuong;

public class GetPhuongRequest {
    public class Codebeautify {
        private String iD_phuong;
        private String tenphuong;
        private String id_huyen;


        // Getter Methods

        public String getID_phuong() {
            return iD_phuong;
        }

        public String getTenphuong() {
            return tenphuong;
        }

        public String getId_huyen() {
            return id_huyen;
        }

        // Setter Methods

        public void setID_phuong(String iD_phuong) {
            this.iD_phuong = iD_phuong;
        }

        public void setTenphuong(String tenphuong) {
            this.tenphuong = tenphuong;
        }

        public void setId_huyen(String id_huyen) {
            this.id_huyen = id_huyen;
        }
    }
}
