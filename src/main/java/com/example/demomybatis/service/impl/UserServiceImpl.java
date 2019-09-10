package com.example.demomybatis.service.impl;

import com.example.demomybatis.entity.User;
import com.example.demomybatis.mapper.TestUserMapper;
import com.example.demomybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TestUserMapper testUserMapper;

    @Override
    public User selectById(User user) {
        return testUserMapper.selectById(user);
    }
}
