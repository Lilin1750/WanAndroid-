package com.example.wanandroid.domain.result;

public class Result<T> {
    //
    private final boolean success;
    private final String message;
    private final T data;

    private Result(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
    public static <T> Result<T> success(T data) {
        return new Result<>(true, null, data);
    }
    public static <T> Result<T> error(String message) {
        return new Result<>(false, message, null);
    }
    //两个静态方法用于请求数据后反馈成功或失败并返回数据

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
