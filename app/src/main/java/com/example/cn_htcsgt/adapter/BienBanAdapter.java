package com.example.cn_htcsgt.adapter;

import android.content.Context;

import com.example.cn_htcsgt.base.CustomBaseAdapter;
import com.example.cn_htcsgt.model.bienban.GetTTBBRespnse;

public class BienBanAdapter  extends CustomBaseAdapter<GetTTBBRespnse.Data> {

    public BienBanAdapter(Context context) {
        super(context);
    }

    @Override
    protected String GetText(GetTTBBRespnse.Data data) {
        return data.iD_BB;

    }
}

