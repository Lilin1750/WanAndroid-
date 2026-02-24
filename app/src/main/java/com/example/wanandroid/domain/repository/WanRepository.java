package com.example.wanandroid.domain.repository;

import com.example.wanandroid.domain.model.Article;
import com.example.wanandroid.domain.model.Banner;
import com.example.wanandroid.domain.model.Tree;
import com.example.wanandroid.domain.model.User;
import com.example.wanandroid.domain.result.PageData;
import com.example.wanandroid.domain.result.Result;
import java.util.List;

public interface WanRepository {
    // 首页
    Result<List<Banner>> getBanners();
    Result<List<Article>> getTopArticles();
    Result<PageData<Article>> getArticles(int page);

    // Tree
    Result<List<Tree>> getTree();
    Result<PageData<Article>> getTreeArticles(int cid, int page);

    // 项目
    Result<PageData<Article>> getProjects(int page);

    // 搜索
    Result<List<String>> getHotKeys();
    Result<PageData<Article>> search(String keyword, int page);

    // 用户
    Result<User> login(String username, String password);
    Result<User> register(String username, String password, String repassword);
    Result<Void> logout();
    Result<PageData<Article>> getCollectArticles(int page);
    Result<Void> collect(String articleId);
    Result<Void> uncollect(String articleId);
}
