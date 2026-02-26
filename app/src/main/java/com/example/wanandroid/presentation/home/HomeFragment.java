package com.example.wanandroid.presentation.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast; // 补充导入

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.example.wanandroid.R;
import com.example.wanandroid.data.remote.RetrofitClient;
import com.example.wanandroid.data.repository.WanRepositoryImpl;
import com.example.wanandroid.domain.model.Article;
import com.example.wanandroid.domain.model.Banner; // 补充导入
import com.example.wanandroid.domain.repository.WanRepository;
import com.example.wanandroid.presentation.home.HomeAdapter;
import com.example.wanandroid.presentation.home.HomeViewModel;
import com.example.wanandroid.presentation.webview.WebviewActivity;


import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private HomeAdapter homeAdapter;
    private HomeViewModel viewModel;
    private final Handler bannerHandler = new Handler(Looper.getMainLooper());
    private Runnable bannerRunnable;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //View实例化
        SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.refresh_layout);
        RecyclerView recyclerView = view.findViewById(R.id.rv_articles);

        //初始化Adapter
        homeAdapter = new HomeAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(homeAdapter);

        //初始化ViewModel并调用
        if (this.viewModel == null) {
            this.viewModel = new HomeViewModel(new WanRepositoryImpl(RetrofitClient.getApi()));
        }
        initObservers(swipeRefreshLayout);

        swipeRefreshLayout.setOnRefreshListener(() ->{
            viewModel.refreshHomeData();//只发起请求
        });

        View fab = getActivity().findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(v -> {
                    recyclerView.smoothScrollToPosition(0);
            });
        }

        homeAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener(){
            @Override
            public void onArticleClick(Article article) {
                Intent intent = new Intent(getActivity(), WebviewActivity.class);
                intent.putExtra("url", article.getLink());
                intent.putExtra("title", article.getTitle());
                startActivity(intent);
            }
            @Override
            public void onBannerClick(Banner banner) {
                Intent intent = new Intent(getActivity(), WebviewActivity.class);
                intent.putExtra("url", banner.getUrl());
                intent.putExtra("title", banner.getTitle());
                startActivity(intent);
            }
        });
    }

    private void initObservers(SwipeRefreshLayout swipeRefreshLayout) {
        // 观察Banner
        viewModel.banners.observe(getViewLifecycleOwner(), banners -> {
            homeAdapter.setBanners(banners);
        });

        // 观察首页文章列表
        viewModel.articles.observe(getViewLifecycleOwner(), articles -> {
            homeAdapter.submitList(articles);
            if (swipeRefreshLayout != null) {
                swipeRefreshLayout.setRefreshing(false); // 数据真正返回，停止转圈
            }
        });

        // 观察报错
        viewModel.error.observe(getViewLifecycleOwner(), errorMsg -> {
            if (errorMsg != null) {
                Toast.makeText(getContext(), errorMsg, Toast.LENGTH_SHORT).show();
                if (swipeRefreshLayout != null) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });

        // 首次进入界面触发一次刷新
        swipeRefreshLayout.setRefreshing(true);
        viewModel.refreshHomeData();
    }
    private void startAutoScroll() {
        // 停止之前的任务，避免重复
        stopAutoScroll();

        bannerRunnable = new Runnable() {
            @Override
            public void run() {
                // 获取 RecyclerView 中第 0 个 Item (即 Banner 所在的 ViewHolder)
                RecyclerView recyclerView = getView().findViewById(R.id.rv_articles);
                RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(0);

                if (viewHolder instanceof HomeAdapter.BannerViewHolder) {
                    ViewPager2 viewPager = ((HomeAdapter.BannerViewHolder) viewHolder).getViewPager();
                    int itemCount = viewPager.getAdapter().getItemCount();
                    if (itemCount > 0) {
                        int nextItem = (viewPager.getCurrentItem() + 1) % itemCount;
                        viewPager.setCurrentItem(nextItem, true);
                    }
                }
                // 每 4 秒执行一次
                bannerHandler.postDelayed(this, 4000);
            }
        };
        bannerHandler.postDelayed(bannerRunnable, 4000);
    }

    private void stopAutoScroll() {
        if (bannerRunnable != null) {
            bannerHandler.removeCallbacks(bannerRunnable);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //可见时自动轮播banner
        startAutoScroll();
    }

    @Override
    public void onPause() {
        super.onPause();
        // 不可见时暂停轮播
        stopAutoScroll();
    }
}
