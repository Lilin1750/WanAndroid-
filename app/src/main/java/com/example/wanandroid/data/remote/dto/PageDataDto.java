package com.example.wanandroid.data.remote.dto;

import java.util.List;

public class PageDataDto<T> {
    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
    private List<T> datas;

    public int getCurPage() {
        return curPage;
    }

    public int getOffset() {
        return offset;
    }

    public boolean isOver() {
        return over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getSize() {
        return size;
    }

    public int getTotal() {
        return total;
    }

    public List<T> getDatas() {
        return datas;
    }

    public boolean hasMore() {
        return !over;
    }
}
