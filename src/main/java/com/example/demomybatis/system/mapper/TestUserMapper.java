package com.example.demomybatis.system.mapper;

import com.example.demomybatis.system.entity.SystemUser;

public interface TestUserMapper {
    SystemUser selectById(SystemUser systemUser);
}
