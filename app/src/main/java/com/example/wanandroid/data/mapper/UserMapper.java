package com.example.wanandroid.data.mapper;

import com.example.wanandroid.domain.model.User;
import com.example.wanandroid.data.remote.dto.UserDto;

import java.util.ArrayList;
import java.util.List;
public class UserMapper {
    //单个User对象转换
    public static User toDomain(UserDto dto) {
        return new User(
                dto.getId(),
                dto.getCoinCount(),
                dto.getUsername(),
                dto.getToken(),
                dto.getRank()
        );
    }

    //User列表转换
    public static List<User> toDomainList(List<UserDto> dtoList) {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < dtoList.size(); i++) {
            UserDto dto = dtoList.get(i);
            list.add(toDomain(dto));
        }
        return list;
    }
}
