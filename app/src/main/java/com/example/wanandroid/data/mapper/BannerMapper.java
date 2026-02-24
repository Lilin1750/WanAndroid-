package com.example.wanandroid.data.mapper;

import com.example.wanandroid.data.remote.dto.BannerDto;
import com.example.wanandroid.domain.model.Banner;
import java.util.List;
import java.util.ArrayList;

public class BannerMapper {

    // 单个Banner对象转换
    public static Banner toDomain(BannerDto dto) {
        return new Banner(
                dto.getId(),
                dto.getDesc(),
                dto.getImagePath(),
                dto.getUrl()
        );
    }

    // Banner列表转换
    public static List<Banner> toDomainList(List<BannerDto> dtoList) {
        List<Banner> list = new ArrayList<>();
        for (int i = 0; i < dtoList.size(); i++) {
            BannerDto dto = dtoList.get(i);
            list.add(toDomain(dto));
        }
        return list;
    }
}