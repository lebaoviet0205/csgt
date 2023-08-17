package com.example.cn_htcsgt;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Listlichsu extends AppCompatActivity {
    TextView t1, t2, t3, t4,t5,t6,t7,t8,t9,t10, t12, t13, t14;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listlichsu);
        InPutData();
    }
    private void InPutData(){
        t1= findViewById(R.id.t1);
        t1.setText(getIntent().getExtras().getString("Mã Cán Bộ :"));

        t2= findViewById(R.id.t2);
        t2.setText(getIntent().getExtras().getString("Biển số :"));

        t3= findViewById(R.id.t3);
        t3.setText(getIntent().getExtras().getString("Số định danh:"));

        t4= findViewById(R.id.t4);
        t4.setText(getIntent().getExtras().getString("Tên chủ xe:"));
        t5= findViewById(R.id.t5);
        t5.setText(getIntent().getExtras().getString("Phường :"));

        t6= findViewById(R.id.t6);
        t6.setText(getIntent().getExtras().getString("Huyện :"));

        t7= findViewById(R.id.t7);
        t7.setText(getIntent().getExtras().getString("Tỉnh :"));

        t8= findViewById(R.id.t8);
        t8.setText(getIntent().getExtras().getString("Quốc Gia :"));


        t9= findViewById(R.id.t9);
        t9.setText(getIntent().getExtras().getString("Tên Lỗi :"));

        t10= findViewById(R.id.t10);
        t10.setText(getIntent().getExtras().getString("Mức phạt: :"));

        t12= findViewById(R.id.t12);
        t12.setText(getIntent().getExtras().getString("Mã Biên Bản :"));
        t13= findViewById(R.id.t13);
        t13.setText(getIntent().getExtras().getString("Ngày Lập :"));

        t14= findViewById(R.id.t14);
        t14.setText(getIntent().getExtras().getString("Nội dung vi phạm: :"));




    }
}