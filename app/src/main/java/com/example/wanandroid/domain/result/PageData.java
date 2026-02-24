package com.example.wanandroid.domain.result;

import java.util.List;

public class PageData <T> {
    private final List<T> datas;
    private final boolean over;
    private final int pageCount;
    private final int curPage;

    public PageData(List<T> datas, boolean over, int pageCount, int curPage) {
        this.datas = datas;
        this.over = over;
        this.pageCount = pageCount;
        this.curPage = curPage;
    }

    public List<T> getDatas() {
        return datas;
    }
    public boolean isOver() {
        return over;
    }
    public int getPageCount() {
        return pageCount;
    }
    public int getCurPage() {
        return curPage;
    }
    public boolean hasMore() {
        return !over;//api返回的over已经进行页数判断了
    }
}
