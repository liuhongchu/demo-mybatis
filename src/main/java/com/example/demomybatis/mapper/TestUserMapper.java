package com.example.demomybatis.mapper;

import com.example.demomybatis.entity.User;
import com.example.demomybatis.provider.UserSqlProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

@Mapper
public interface TestUserMapper {

    @SelectProvider(type = UserSqlProvider.class)
    User selectById(User user);
}
