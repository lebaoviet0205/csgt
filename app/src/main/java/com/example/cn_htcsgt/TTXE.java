package com.example.cn_htcsgt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cn_htcsgt.Intent.AppUtil;

public class TTXE extends AppCompatActivity implements View.OnClickListener {
    TextView timkiem, BKS, Seri, TenChuXe, DiaChi, Brand, Model, Color, EngineNo, ChassisNo, DateStat, DateEnd, TrangThai; // noi hien thi anh xa
    //BKS,Seri,TenChuXe,DiaChi,Brand,Model,Color,EngineNo,ChassisNo,DateStat,DateEnd,TrangThai;
    Button btnLBB;
    String ID_Nganh, bks1 , tenchuxe2;
    TextView tt6, t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        t2 = findViewById(R.id.t2);
        ID_Nganh = getIntent().getExtras().getString("ID_Nganh");
        getSupportActionBar().setTitle("Activity 1");
        setContentView(R.layout.activity_ttxe);
        InPutData();
//btnLBB
     ///   tt2 = findViewById(R.id.t2);
        btnLBB = findViewById(R.id.btnLBB);
        btnLBB.setOnClickListener(this);


    }
    private void GetBKS(){
        Intent i = new  Intent(TTXE.this,LapBB.class);


        i.putExtra("BKS",bks1);



        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        DateEnd.setText(AppUtil.BKS);
    }

    private void InPutData(){
        timkiem = findViewById(R.id.t1);
        String timkiem1 = getIntent().getExtras().getString("Tìm kiếm :");
        timkiem.setText(timkiem1);

        BKS= findViewById(R.id.t2);
        BKS.setText(getIntent().getExtras().getString("Biển số :"));

        Seri= findViewById(R.id.t3);
        Seri.setText(getIntent().getExtras().getString("SeriNo :"));

        TenChuXe= findViewById(R.id.t4);
        TenChuXe.setText(getIntent().getExtras().getString("Tên chủ xe :"));

        DiaChi= findViewById(R.id.t5);
        DiaChi.setText(getIntent().getExtras().getString("Địa chỉ :"));

        Brand= findViewById(R.id.t6);
        Brand.setText(getIntent().getExtras().getString("Hãng xe :"));

        Model= findViewById(R.id.t7);
        Model.setText(getIntent().getExtras().getString("Loại xe :"));

        Color= findViewById(R.id.t8);
        Color.setText(getIntent().getExtras().getString("Màu xe :"));

        EngineNo= findViewById(R.id.t9);
        EngineNo.setText(getIntent().getExtras().getString("EngineNo :"));

        ChassisNo= findViewById(R.id.t10);
        ChassisNo.setText(getIntent().getExtras().getString("ChassisNo :"));

        DateStat= findViewById(R.id.t11);
        DateStat.setText(getIntent().getExtras().getString("DateStart :"));

        DateEnd= findViewById(R.id.t12);
        DateEnd.setText(getIntent().getExtras().getString("DateEnd :"));
        bks1=DateEnd.getText().toString();

        TrangThai= findViewById(R.id.t13);
        TrangThai.setText(getIntent().getExtras().getString("TrangThai :"));

    }

    @Override
    public void onClick(View v) {
        GetBKS();
    }
}