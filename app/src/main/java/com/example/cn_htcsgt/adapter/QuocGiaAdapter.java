package com.example.cn_htcsgt.adapter;

import android.content.Context;

import com.example.cn_htcsgt.base.CustomBaseAdapter;
import com.example.cn_htcsgt.model.QuocGia.GetQuocGiaResponse;

public class QuocGiaAdapter extends CustomBaseAdapter<GetQuocGiaResponse.Data> {

    public QuocGiaAdapter(Context context) {
        super(context);
    }

    @Override
    protected String GetText(GetQuocGiaResponse.Data data) {
        return data.tenChinhThuc;
    }
}
