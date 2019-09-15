package com.example.demomybatis.service;

import com.example.demomybatis.entity.User;

import java.util.ArrayList;

public interface UserService {
    User selectById(User user);

    ArrayList<User> findByAll();
}
