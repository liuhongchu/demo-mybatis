package com.example.demomybatis.controller;

import com.example.demomybatis.entity.User;
import com.example.demomybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("UserInfo")
    @GetMapping
    public User  findById(){
        User user = new User();
        user.setId(1);
        user = userService.selectById(user);
        System.out.println("userId:"+user.getId()+"userIdName"+user.getName());
        return user;
    }
}
