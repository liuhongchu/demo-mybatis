package com.example.demomybatis.system.controller;

import com.example.demomybatis.system.entity.SystemUser;
import com.example.demomybatis.system.service.SystemUserRoleService;
import com.example.demomybatis.system.service.SystemUserService;
import com.example.demomybatis.utils.PageUtils;
import com.example.demomybatis.utils.ResultUtils;
import com.example.demomybatis.utils.ShiroUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system/user")
@Slf4j
@Api(tags = "系统用户接口")
public class SystemUserController extends BaseController {
    @Autowired
    private SystemUserService systemUserService;
    @Autowired
    private SystemUserRoleService systemUserRoleService;

    /**
     * 所有用户列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:user:list")
    @ApiOperation("所有用户列表")
    public ResultUtils list(@RequestParam Map<String, Object> params) {
        PageUtils page = systemUserService.queryPage(params);

        return ResultUtils.ResultOk().put("page", page);
    }

    /**
     * 获取登录的用户信息
     */
    @GetMapping("/info")
    @ApiOperation("获取登录的用户信息")
    public ResultUtils info() {
        return ResultUtils.ResultOk().put("user", getUser());
    }

    /**
     * 修改登录用户密码
     */
    @PostMapping("/password")
    @ApiOperation("更新登录用户密码")
    public ResultUtils password(String password, String newPassword) {
        //原密码
        password = ShiroUtils.sha256(password, getUser().getSalt());
        //新密码
        newPassword = ShiroUtils.sha256(newPassword, getUser().getSalt());

        //更新密码
        boolean flag = systemUserService.updatePassword(getUserId(), password, newPassword);
        if (!flag) {
            return ResultUtils.ResultError("原密码不正确");
        }

        return ResultUtils.ResultOk();
    }

    /**
     * 用户信息
     */
    @GetMapping("/info/{userId}")
    @ApiOperation("获取用户信息")
    public ResultUtils info(@PathVariable("userId") Long userId) {
        SystemUser user = systemUserService.getById(userId);
        //获取用户所属的角色列表
        List<Long> roleIdList = systemUserRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIdList);
        return ResultUtils.ResultOk().put("user", user);
    }

    /**
     * 保存用户
     */
    @PostMapping("/save")
    @ApiOperation("保存用户信息")
    public ResultUtils save(@RequestBody SystemUser user) {
//        ValidatorUtils.validateEntity(user, AddGroup.class);
        systemUserService.saveUser(user);
        return ResultUtils.ResultOk();
    }

    /**
     * 修改用户
     */
    @PostMapping("/update")
    @ApiOperation("更新用户信息")
    public ResultUtils update(@RequestBody SystemUser user) {
//        ValidatorUtils.validateEntity(user, UpdateGroup.class);
        systemUserService.update(user);

        return ResultUtils.ResultOk();
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/delete")
    @ApiOperation("删除用户信息")
    public ResultUtils delete(@RequestBody Long[] userIds) {
        if (ArrayUtils.contains(userIds, 1L)) {
            return ResultUtils.ResultError("系统管理员不能删除");
        }

        if (ArrayUtils.contains(userIds, getUserId())) {
            return ResultUtils.ResultError("当前用户不能删除");
        }

        systemUserService.removeByIds(Arrays.asList(userIds));

        return ResultUtils.ResultOk();
    }
}
