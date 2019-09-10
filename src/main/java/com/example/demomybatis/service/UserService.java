package com.example.demomybatis.service;

import com.example.demomybatis.entity.User;

public interface UserService {
    User selectById(User user);
}
