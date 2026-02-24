package com.example.wanandroid.data.remote.dto;

import java.util.List;

public class TreeDto {
    private int id;
    private String name;
    private List<TreeDto> children;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<TreeDto> getChildren() {
        return children;
    }
}
