package com.example.cn_htcsgt.adapter;

import android.content.Context;

import com.example.cn_htcsgt.base.CustomBaseAdapter;
import com.example.cn_htcsgt.model.TinhThanh.GetTinhRespone;


public class ThanhPhoAdapter extends CustomBaseAdapter<GetTinhRespone.Data> {

    public ThanhPhoAdapter(Context context) {
        super(context);
    }

    @Override
    protected String GetText(GetTinhRespone.Data data) {
        return data.tentinhthanh;
    }
}