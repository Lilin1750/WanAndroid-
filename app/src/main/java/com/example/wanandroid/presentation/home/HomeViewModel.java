package com.example.wanandroid.presentation.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wanandroid.domain.model.Article;
import com.example.wanandroid.domain.model.Banner;
import com.example.wanandroid.domain.repository.WanRepository;
import com.example.wanandroid.domain.result.PageData;
import com.example.wanandroid.domain.result.Result;


import java.util.List;

public class HomeViewModel extends ViewModel {
    private final WanRepository repository;

    private final MutableLiveData<List<Banner>> _banners = new MutableLiveData<>();
    public LiveData<List<Banner>> banners = _banners;

    private final MutableLiveData<List<Article>> _articles = new MutableLiveData<>();
    public LiveData<List<Article>> articles = _articles;

    private final MutableLiveData<String> _error = new MutableLiveData<>();
    public LiveData<String> error = _error;

    public HomeViewModel(WanRepository repository) {
        this.repository = repository;
    }

    public void refreshHomeData() {
        new Thread(() -> {
            // 获取轮播图
            Result<List<Banner>> bannerResult = repository.getBanners();
            if (bannerResult.isSuccess()) {
                List<Banner> bannerList = bannerResult.getData();
                _banners.postValue(bannerList);

                Log.d("BannerTest", "輪播圖數量: " + (bannerList != null ? bannerList.size() : 0));
            }

            // 获取首页文章
            Result<PageData<Article>> articleResult = repository.getArticles(0);
            if (articleResult.isSuccess()) {
                _articles.postValue(articleResult.getData().getDatas());
            } else {
                _error.postValue(articleResult.getMessage());
            }
        }).start();
    }
}