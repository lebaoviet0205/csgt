package com.example.cn_htcsgt.model.user;

public class GetTokenRequest {

    public GetTokenRequest(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public String userName;
    public String passWord;
}
