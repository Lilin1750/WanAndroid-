package com.example.wanandroid.data.remote.api;

import com.example.wanandroid.data.remote.dto.ArticleDto;
import com.example.wanandroid.data.remote.dto.BaseResponse;
import com.example.wanandroid.data.remote.dto.BannerDto;
import com.example.wanandroid.data.remote.dto.PageDataDto;
import com.example.wanandroid.data.remote.dto.TreeDto;
import com.example.wanandroid.data.remote.dto.UserDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WanApi {
    String BASE_URL = "https://www.wanandroid.com/";

    @GET("banner/json")//注解，告诉Retrofit这是get请求
    Call<BaseResponse<List<BannerDto>>> getBanners();//没有方法体，不用写public，Call代表泛型
    @GET("article/list/{page}/json")
    Call<BaseResponse<PageDataDto<ArticleDto>>> getArticles(@Path("page") int page);

    @GET("tree/json")
    Call<BaseResponse<List<TreeDto>>> getTree();

    @FormUrlEncoded
    @POST("user/login")
    Call<BaseResponse<UserDto>> login(@Field("username") String name, @Field("password") String pw);
    // 搜索
    @FormUrlEncoded
    @POST("article/query/{page}/json")
    Call<BaseResponse<PageDataDto<ArticleDto>>> search(@Path("page") int page, @Field("k") String keyword);

    // 收藏
    @POST("lg/collect/{id}/json")
    Call<BaseResponse<Object>> collect(@Path("id") int id);

    //置顶文章
    @GET("article/top/json")
    Call<BaseResponse<List<ArticleDto>>> getTopArticles();
}