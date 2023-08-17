package com.example.cn_htcsgt.base;

public interface IApiCallBack {
    void onStartCallApi();
    void onSuccess(String TAG, Object data, String ex);
    void onUnauthorized();
    void onFail();
    void onEndCallApi();
}
