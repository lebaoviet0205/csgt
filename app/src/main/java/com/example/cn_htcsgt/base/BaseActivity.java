package com.example.cn_htcsgt.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cn_htcsgt.helper.SharedPreferencesHelper;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferencesHelper.getInstance(this);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        InitView();
    }

    protected abstract void InitView();

    protected abstract int getLayoutId();
}