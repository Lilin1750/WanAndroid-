package com.example.wanandroid.presentation.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.wanandroid.domain.model.Banner;
import com.example.wanandroid.domain.repository.WanRepository; // 匹配你的拼写
import com.example.wanandroid.domain.result.Result;
import java.util.List;

public class MainViewModel extends ViewModel {
    private final WanRepository repository;

    private final MutableLiveData<List<Banner>> _banners = new MutableLiveData<>();
    public LiveData<List<Banner>> banners = _banners;

    private final MutableLiveData<String> _error = new MutableLiveData<>();
    public LiveData<String> error = _error;

    // 添加 isLoading 状态
    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();
    public LiveData<Boolean> isLoading = _isLoading;

    public MainViewModel(WanRepository repository) {
        this.repository = repository;
    }

    public void fetchBanners() {
        _isLoading.postValue(true); // 开始加载
        new Thread(() -> {
            try {
                Result<List<Banner>> result = repository.getBanners();
                if (result.isSuccess()) {
                    _banners.postValue(result.getData());
                } else {
                    _error.postValue(result.getMessage());
                }
            } catch (Exception e) {
                _error.postValue("网络请求异常: " + e.getMessage());
            } finally {
                _isLoading.postValue(false); // 加载结束，隐藏进度条
            }
        }).start();
    }
}