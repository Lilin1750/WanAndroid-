//文章的model
package com.example.wanandroid.domain.model;

import java.util.List;
import java.util.Objects;

public class Article {
    private final String id;
    private final String title;
    private final String author;
    private final String link;
    private final String niceDate;
    private final boolean collect;
    private final int type;

    private final List<Tag> tags;



    public Article(String title, String link, String author, String id, String niceDate, boolean collect, int type, List<Tag> tags) {
            this.title = title;
            this.link = link; // 原代码这里参数名是 content，赋值给了 link
            this.author = author;
            this.id = id;
            this.niceDate = niceDate;
            this.collect = collect;
            this.type = type;
            this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getAuthor() {
        return author;
    }

    public String getId() {
        return id;
    }

    public String getNiceDate() {
        return niceDate;
    }

    public boolean isCollect() {
        return collect;
    }

    public int getType() {
        return type;
    }

    public List<Tag> getTags() {
        return tags;
    }

    //业务方法

    //判断置顶<int转boolean>
    public boolean isTop() {
        return type == 1;
    }

    //显示作者，优先显示公众号的作者(在tag的属性里
    public String getDisplayAuthor() {
        if (!tags.isEmpty() && tags.get(0).getName() != null){
            return tags.get(0).getName();
        }else
            return author;
    }

    //改变收藏状态(返回新对象
    public Article withCollect(boolean newCollect) {
        return new Article(title, link, author, id, niceDate, newCollect, type, tags);
    }

    //三个object方法

    //便于输出log
    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", id='" + id + '\'' +'}' ;
    }

    //判断两个article是否相等
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;//先判断类型不相等
        Article article = (Article) o;//再转换类型
        return Objects.equals(id, article.id);//判断id是否相等，用object的方法更安全，null也能比较
    }

    //计算哈希值
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
