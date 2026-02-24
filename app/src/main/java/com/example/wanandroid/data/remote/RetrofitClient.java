package com.example.wanandroid.data.remote;

import com.example.wanandroid.data.remote.api.WanApi;

import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static WanApi wanApi;

    public static WanApi getApi() {
        if (wanApi == null) {
            retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(WanApi.BASE_URL) // 設置基本 URL
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            wanApi = retrofit.create(WanApi.class);
        }
        return wanApi;
    }
}