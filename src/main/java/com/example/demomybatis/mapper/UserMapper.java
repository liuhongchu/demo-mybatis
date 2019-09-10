package com.example.demomybatis.mapper;

import com.example.demomybatis.entity.User;

public interface UserMapper {
    User selectById(User user);
}
