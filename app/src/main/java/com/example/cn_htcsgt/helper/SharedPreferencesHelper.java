package com.example.cn_htcsgt.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {

    public SharedPreferences sharedPreferences = null;
    public SharedPreferences.Editor editor = null;
    private static SharedPreferencesHelper Instance = null;
    public static SharedPreferencesHelper getInstance(Context context) {
        if (Instance==null) {
            Instance = new SharedPreferencesHelper();
            Instance.sharedPreferences = context.getSharedPreferences("DataXmlLocal",  Context.MODE_PRIVATE);
            Instance.editor = Instance.sharedPreferences.edit();
        }
        return Instance;
    }

    public String getToken(){
        return  "Bearer " + getString("Token_Api");
    }
    public void setToken(String value){
        putString("Token_Api",value);
    }
    public static SharedPreferencesHelper getInstance() {
        return Instance;
    }

    public String getString(String key) {
        String value =  Instance.sharedPreferences.getString(key,null);
        return value;
    }
    public void putString(String key,String value) {
        Instance.editor.putString(key,value);
        editor.commit();
    }
    public void remove(String key) {
        Instance.editor.remove(key);
        editor.commit();
    }
}
