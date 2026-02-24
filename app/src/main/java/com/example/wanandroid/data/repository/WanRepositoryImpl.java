package com.example.wanandroid.data.repository;

import com.example.wanandroid.data.mapper.ArticleMapper;
import com.example.wanandroid.data.remote.api.WanApi;
import com.example.wanandroid.data.remote.dto.ArticleDto;
import com.example.wanandroid.data.remote.dto.BaseResponse;
import com.example.wanandroid.data.remote.dto.PageDataDto;
import com.example.wanandroid.domain.model.Article;
import com.example.wanandroid.domain.model.Banner;
import com.example.wanandroid.domain.repository.WanRepository;
import com.example.wanandroid.domain.result.PageData;
import com.example.wanandroid.domain.result.Result;
import com.example.wanandroid.domain.model.User;
import com.example.wanandroid.domain.model.Tree;

import java.util.List;
import retrofit2.Response;

public class WanRepositoryImpl implements WanRepository {

    private final WanApi api;

    public WanRepositoryImpl(WanApi api) {
        this.api = api;
    }

    @Override
    public Result<PageData<Article>> getArticles(int page) {
        try {
            List<Article> allArticles = new java.util.ArrayList<>();
            if (page == 0) {
                retrofit2.Response<BaseResponse<List<ArticleDto>>> topResponse = api.getTopArticles().execute();
                if (topResponse.isSuccessful() && topResponse.body() != null) {
                    if (topResponse.body().getErrorCode() == 0) {
                        // 置顶文章转换并加入列表
                        allArticles.addAll(ArticleMapper.toDomainList(topResponse.body().getData()));
                    }
                }
            }
            // 同步執行請求
            Response<BaseResponse<PageDataDto<ArticleDto>>> response = api.getArticles(page).execute();
            if (response.isSuccessful() && response.body() != null) {
                BaseResponse<PageDataDto<ArticleDto>> base = response.body();
                if (base.getErrorCode() == 0) {
                    PageDataDto<ArticleDto> dataDto = base.getData();
                    allArticles.addAll(ArticleMapper.toDomainList(dataDto.getDatas()));
                    return Result.success(new PageData<>(allArticles, dataDto.isOver(), dataDto.getPageCount(), dataDto.getCurPage()));
                }
                return Result.error(base.getErrorMsg());
            }
        } catch (Exception e) {
            return Result.error("網絡異常：" + e.getMessage());
        }
        return Result.error("未知錯誤");
    }

    @Override
    public Result<List<Article>> getTopArticles() {
        try {
            Response<BaseResponse<List<ArticleDto>>> response = api.getTopArticles().execute();
            if (response.isSuccessful() && response.body() != null) {
                BaseResponse<List<ArticleDto>> base = response.body();
                if (base.getErrorCode() == 0) {
                    return Result.success(ArticleMapper.toDomainList(base.getData()));
                }
                return Result.error(base.getErrorMsg());
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
        return Result.error("獲取置頂失敗");
    }

    @Override
    public Result<List<Banner>> getBanners() {
        try {
            // 1. 執行同步請求
            retrofit2.Response<com.example.wanandroid.data.remote.dto.BaseResponse<List<com.example.wanandroid.data.remote.dto.BannerDto>>>
                    response = api.getBanners().execute();

            if (response.isSuccessful() && response.body() != null) {
                com.example.wanandroid.data.remote.dto.BaseResponse<List<com.example.wanandroid.data.remote.dto.BannerDto>>
                        baseResponse = response.body();

                if (baseResponse.getErrorCode() == 0 && baseResponse.getData() != null) {
                    // 將 DTO 轉換為 Domain Model
                    List<Banner> domainBanners = new java.util.ArrayList<>();
                    for (com.example.wanandroid.data.remote.dto.BannerDto dto : baseResponse.getData()) {
                        domainBanners.add(new Banner(
                                dto.getId(),
                                dto.getDesc(),
                                dto.getImagePath(),
                                dto.getUrl()
                        ));
                    }
                    return Result.success(domainBanners);
                }
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
        return Result.error("獲取輪播圖失敗");
    }

    // 搜索功能實現
    @Override
    public Result<PageData<Article>> search(String keyword, int page) {
        try {
            Response<BaseResponse<PageDataDto<ArticleDto>>> response = api.search(page, keyword).execute();
            if (response.isSuccessful() && response.body() != null) {
                BaseResponse<PageDataDto<ArticleDto>> base = response.body();
                if (base.getErrorCode() == 0) {
                    PageDataDto<ArticleDto> dataDto = base.getData();
                    List<Article> articles = ArticleMapper.toDomainList(dataDto.getDatas());
                    return Result.success(new PageData<>(articles,dataDto.isOver(),dataDto.getPageCount(),dataDto.getCurPage()));
                }
            }
        } catch (Exception e) { return Result.error(e.getMessage()); }
        return Result.error("搜索失敗");
    }

    @Override public Result<List<Tree>> getTree() { return null; }
    @Override public Result<PageData<Article>> getTreeArticles(int cid, int page) { return null; }
    @Override public Result<PageData<Article>> getProjects(int page) { return null; }
    @Override public Result<List<String>> getHotKeys() { return null; }
    @Override public Result<User> login(String username, String password) { return null; }
    @Override public Result<User> register(String username, String password, String repassword) { return null; }
    @Override public Result<Void> logout() { return null; }
    @Override public Result<PageData<Article>> getCollectArticles(int page) { return null; }
    @Override public Result<Void> collect(String articleId) { return null; }
    @Override public Result<Void> uncollect(String articleId) { return null; }
}