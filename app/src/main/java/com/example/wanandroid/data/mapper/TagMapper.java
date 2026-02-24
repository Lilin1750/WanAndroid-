package com.example.wanandroid.data.mapper;

import com.example.wanandroid.domain.model.Tag;
import com.example.wanandroid.data.remote.dto.TagDto;

import java.util.ArrayList;
import java.util.List;


public class TagMapper {
    //单个Tag对象转换
    public static Tag toDomain(TagDto dto) {
        return new Tag(
                dto.getName(),
                dto.getUrl()
        );
    }
    //Tag列表转换
    public static List<Tag> toDomainList(List<TagDto> dtoList) {
        List<Tag> list = new ArrayList<>();
        for (int i = 0; i < dtoList.size(); i++) {
            TagDto dto = dtoList.get(i);
            list.add(toDomain(dto));
        }
        return list;
    }
}
