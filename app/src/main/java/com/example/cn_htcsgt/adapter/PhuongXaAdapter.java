package com.example.cn_htcsgt.adapter;

import android.content.Context;

import com.example.cn_htcsgt.base.CustomBaseAdapter;
import com.example.cn_htcsgt.model.Phuong.GetPhuongResonse;

public class PhuongXaAdapter extends CustomBaseAdapter<GetPhuongResonse.Data> {

    public PhuongXaAdapter(Context context) {
        super(context);
    }

    @Override
    protected String GetText(GetPhuongResonse.Data data) {
        return data.tenphuong;
    }
}