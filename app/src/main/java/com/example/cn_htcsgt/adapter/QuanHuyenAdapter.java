package com.example.cn_htcsgt.adapter;

import android.content.Context;

import com.example.cn_htcsgt.base.CustomBaseAdapter;
import com.example.cn_htcsgt.model.Huyen.GetHuyenResPonse;


public class QuanHuyenAdapter extends CustomBaseAdapter<GetHuyenResPonse.Data> {

    public QuanHuyenAdapter(Context context) {
        super(context);
    }

    @Override
    protected String GetText(GetHuyenResPonse.Data data) {
        return data.tenhuyen;
    }
}