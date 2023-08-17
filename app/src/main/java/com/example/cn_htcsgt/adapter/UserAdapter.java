package com.example.cn_htcsgt.adapter;

import android.content.Context;

import com.example.cn_htcsgt.base.CustomBaseAdapter;
import com.example.cn_htcsgt.model.user.GetUserResponse;

public class UserAdapter extends CustomBaseAdapter<GetUserResponse.Data> {

    public UserAdapter(Context context) {
        super(context);
    }

    @Override
    protected String GetText(GetUserResponse.Data data) {
        return data.id;
    }
}
