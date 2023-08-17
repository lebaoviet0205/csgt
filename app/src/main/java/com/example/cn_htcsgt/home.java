package com.example.cn_htcsgt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class home extends AppCompatActivity {
    private ImageView bbbutton,imgBienban,mapbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mapbutton=findViewById(R.id.mapbutton);
        bbbutton=findViewById(R.id.scanButton);
        imgBienban=findViewById(R.id.imgBienban);
        imgBienban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(home.this, TTBBActivity.class);
                startActivity(i);
            }
        });
        bbbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(home.this, scanner.class);
                startActivity(i);
            }
        });
        mapbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(home.this, MapsActivity.class);
                startActivity(i);
            }
        });
    }
}