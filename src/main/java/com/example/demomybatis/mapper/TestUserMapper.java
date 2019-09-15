package com.example.demomybatis.mapper;

import com.example.demomybatis.entity.User;
import com.example.demomybatis.provider.UserSqlProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface TestUserMapper {

    @SelectProvider(type = UserSqlProvider.class ,method = "selectById")
    User selectById(User user);

    @SelectProvider(type = UserSqlProvider.class ,method = "findByAll" )
    ArrayList<User> findByAll();
}
