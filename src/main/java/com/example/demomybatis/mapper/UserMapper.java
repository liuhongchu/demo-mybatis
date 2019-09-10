package com.example.demomybatis.mapper;

import com.example.demomybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;


public interface UserMapper {
    User selectById(User user);
}
