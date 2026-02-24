//Gson（谷歌的json解析库，Retrofit内含）默认无参构造，区别于model使用final，必须使用传参数初始化
//dto是data transfer object,是一种映射，是Retrofit将api返回的json转换为java对象的模板
package com.example.wanandroid.data.remote.dto;

public class BaseResponse<T>{
    private int errorCode;
    private String errorMsg;
    private T data;

    public boolean isSuccess() {
        return errorCode == 0;
    }

    public int getErrorCode() {
        return errorCode;
    }
    public String getErrorMsg() {
        return errorMsg;
    }
    public T getData() {
        return data;
    }
}
