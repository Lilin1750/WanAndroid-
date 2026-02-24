package com.example.wanandroid.data.mapper;

import com.example.wanandroid.data.remote.dto.TreeDto;
import com.example.wanandroid.domain.model.Tree;
import java.util.List;
import java.util.ArrayList;
public class TreeMapper {
    public static Tree toDomain(TreeDto dto) {
        //先将Dto的List<TreeDto>转换为Domain的List<Tree>
        List<Tree> domainChildren = new ArrayList<>();
        if (dto.getChildren() != null) {
            for (int i = 0; i < dto.getChildren().size(); i++) {
                TreeDto childDto = dto.getChildren().get(i);
                domainChildren.add(toDomain(childDto));
            }
        }
        //在返回新的Tree
        return new Tree(
                dto.getId(),
                dto.getName(),
                domainChildren
        );
    }
    public static List<Tree> toDomainList(List<TreeDto> dtoList) {
        List<Tree> list = new ArrayList<>();
        for (int i = 0; i < dtoList.size(); i++) {
            TreeDto dto = dtoList.get(i);
            list.add(toDomain(dto));
        }
        return list;
    }
}
