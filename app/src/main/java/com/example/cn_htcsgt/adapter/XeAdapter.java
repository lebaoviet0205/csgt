package com.example.cn_htcsgt.adapter;

import android.content.Context;

import com.example.cn_htcsgt.base.CustomBaseAdapter;
import com.example.cn_htcsgt.model.xe.GetXeResponse;

public class XeAdapter extends CustomBaseAdapter<GetXeResponse.Data> {

    public XeAdapter(Context context) {
        super(context);
    }

    @Override
    protected String GetText(GetXeResponse.Data data) {
        return data.bks;
    }
}

