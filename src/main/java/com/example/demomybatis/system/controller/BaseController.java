package com.example.demomybatis.system.controller;

import com.example.demomybatis.system.entity.SystemUser;
import org.apache.shiro.SecurityUtils;

/**
 * @liuhongchun
 * @Date 2019/9/15  20:04
 * @des
 */
public abstract class BaseController {
    protected SystemUser getUser() {
        return (SystemUser) SecurityUtils.getSubject().getPrincipal();
    }

    protected Long getUserId() {
        return getUser().getUserId();
    }

    protected Long getDeptId() {
        return getUser().getDeptId();
    }
}
