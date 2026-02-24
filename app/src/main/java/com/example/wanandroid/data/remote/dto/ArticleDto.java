package com.example.wanandroid.data.remote.dto;

import java.util.List;

public class ArticleDto {
    private int id;
    private String title;
    private String author;    // 原作者
    private String shareUser; // 分享者
    private String niceDate;
    private String link;      // 文章网页链接
    private String envelopePic; // 缩略图链接
    private boolean collect;
    private List<TagDto> tags;
    private int type;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getNiceDate() {
        return niceDate;
    }

    public String getDisplayAuthor() {
        return (author != null && !author.isEmpty()) ? author : shareUser;
    }

    public String getLink() { return link; }
    public String getEnvelopePic() { return envelopePic; }
    public boolean isCollect() { return collect; }
    public List<TagDto> getTags() { return tags; }
    public int getType() { return type; }
    public String getShareUser() { return shareUser; }
    public String getAuthor() { return author; }
}
