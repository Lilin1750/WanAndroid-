package com.example.wanandroid.data.mapper;

import com.example.wanandroid.data.remote.dto.ArticleDto;
import com.example.wanandroid.domain.model.Article;
import java.util.ArrayList;
import java.util.List;

public class ArticleMapper {

    public static Article toDomain(ArticleDto dto) {
        if (dto == null) return null;

        // 严格对应 Article.java 的构造函数 (共 8 个参数)
        return new Article(
                dto.getTitle(),                // 1. title
                dto.getLink(),                 // 2. link
                dto.getDisplayAuthor(),        // 3. author (优先作者或分享者)
                String.valueOf(dto.getId()),   // 4. id (int 转 String)
                dto.getNiceDate(),             // 5. niceDate
                dto.isCollect(),               // 6. collect
                dto.getType(),                 // 7. type
                TagMapper.toDomainList(dto.getTags()) // 8. tags
        );
    }

    public static List<Article> toDomainList(List<ArticleDto> dtoList) {
        List<Article> list = new ArrayList<>();
        if (dtoList != null) {
            for (ArticleDto dto : dtoList) {
                list.add(toDomain(dto));
            }
        }
        return list;
    }
}