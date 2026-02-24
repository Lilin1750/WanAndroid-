package com.example.wanandroid.data.remote.dto;

public class UserDto {
    private int id;
    private int coinCount;//用户积分
    private String username;
    private String token;//用户登录凭证
    private String rank;//用户排名

    public int getId() {
        return id;
    }

    public int getCoinCount() {
        return coinCount;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public String getRank() {
        return rank;
    }
}
