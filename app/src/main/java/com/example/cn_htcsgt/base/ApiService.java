package com.example.cn_htcsgt.base;

import com.example.cn_htcsgt.ApiClient;
import com.example.cn_htcsgt.helper.SharedPreferencesHelper;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public abstract class ApiService<Response> implements Callback<ResponseBody> {
    private IApiCallBack Listener;
    private Object objectRequest;
    private final Type typeResponse;
    private HttpMethod httpMethod = HttpMethod.Get;
    public ApiService() {
        Type mySuperclass = getClass().getGenericSuperclass();
        this.typeResponse = ((ParameterizedType)mySuperclass).getActualTypeArguments()[0];
    }
    public ApiService setListener(IApiCallBack Listener) {
        this.Listener = Listener;
        return this;
    }
    public ApiService setRequest(Object objectRequest) {
        this.objectRequest = objectRequest;
        return this;
    }
    public ApiService setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
        return this;
    }
    protected Map<String, String> getHeaders() {
        Map<String, String> map = new HashMap();
        map.put("Authorization", SharedPreferencesHelper.getInstance().getToken());
        return map;
    }
    public void CallApi(){
        if(this.Listener!=null)Listener.onStartCallApi();
        IApiService apiService = ApiClient.getClient().create(IApiService.class);
        Call<ResponseBody> call;
        switch (this.httpMethod){
            case Post:
                call = apiService.post(getUrl(),objectRequest,getHeaders()) ;
                break;
            default:
                call = apiService.get(getUrl(),getParams(objectRequest),getHeaders());
                break;
        }
        call.enqueue(this);
    }

    protected String getTag(){
        return getUrl();
    }

    protected abstract String getUrl();
    private Map<String, String> getParams(Object object){
        Map<String, String> map = new HashMap();
        if(object!=null)
            for (Field f: getAllModelFields(object.getClass())) {
                f.setAccessible(true);
                try {
                    map.put(f.getName(),String.valueOf(f.get(object)));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        return map;
    }
    private List<Field> getAllModelFields(Class aClass) {
        List<Field> fields = new ArrayList<>();
        while (aClass!=null&&aClass!= java.lang.Object.class){
            Collections.addAll(fields, aClass.getDeclaredFields());
            aClass = aClass.getSuperclass();
        }
        return fields;
    }

    @Override
    public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
        if(response.isSuccessful()){
            try {
                Object responseData = getObject(response.body().string(),typeResponse);
                if(this.Listener!=null)Listener.onSuccess(getTag(),responseData,"");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            switch (response.code()){
                case 401:
                    if(this.Listener!=null)Listener.onUnauthorized();
                    break;
                default:
                    if(this.Listener!=null)Listener.onFail();
                    break;
            }
        }
        if(this.Listener!=null)Listener.onEndCallApi();
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {

        if(this.Listener!=null)Listener.onFail();
        if(this.Listener!=null)Listener.onEndCallApi();
    }
    private  <T> T getObject(String value,Class<T> tClass){
        try {
            Gson gson = new Gson();
            return gson.fromJson(value , tClass);
        }catch (Exception e){
            return null;
        }
    }

    private <T> T getObject(String value, Type integerType){
        try {
            Gson gson = new Gson();
            return gson.fromJson(value , integerType);
        }catch (Exception e){
            return null;
        }
    }
}
