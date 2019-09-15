package com.example.demomybatis.controller;

import com.example.demomybatis.entity.User;
import com.example.demomybatis.service.UserService;
import com.example.demomybatis.utils.SecurityParameterUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/")
@Slf4j
@Api(tags="用户接口")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("UserInfo")
    @GetMapping
    @ApiOperation("获取用户信息")
    @ResponseBody
    public User  findById(User info){
        User user = new User();
        user.setId(user.getId());
        return  userService.selectById(user);
    }

    @RequestMapping("AllUserInfo")
    @GetMapping
    @ApiOperation("获取全部用户信息")
    @ResponseBody
    public ArrayList<User> findByAll(){
        return  userService.findByAll();
    }
}
