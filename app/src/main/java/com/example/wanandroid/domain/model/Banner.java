//Banner的model
package com.example.wanandroid.domain.model;

public class Banner {
    private final int id;
    private final String title;
    private final String imagePath;
    private final String url;

    public Banner(int id, String title, String imagePath, String url) {
        this.id = id;
        this.title = title;
        this.imagePath = imagePath;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getUrl() {
        return url;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", url='" + url + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Banner banner = (Banner) o;
        return id == banner.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
