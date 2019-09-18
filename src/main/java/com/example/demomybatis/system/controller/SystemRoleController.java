package com.example.demomybatis.system.controller;

import com.example.demomybatis.system.entity.SystemRole;
import com.example.demomybatis.system.service.SystemRoleDeptService;
import com.example.demomybatis.system.service.SystemRoleMenuService;
import com.example.demomybatis.system.service.SystemRoleService;
import com.example.demomybatis.utils.PageUtils;
import com.example.demomybatis.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @liuhongchun
 * @Date 2019/9/15  17:02
 */
@RestController
@RequestMapping("/system/role")
@Slf4j
@Api(tags="系统角色接口")
public class SystemRoleController extends BaseController {
    @Autowired
    private SystemRoleService systemRoleService;
    @Autowired
    private SystemRoleMenuService systemRoleMenuService;
    @Autowired
    private SystemRoleDeptService systemRoleDeptService;

    /**
     * 角色列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:role:list")
    @ApiOperation("角色列表")
    public ResultUtils list(@RequestParam Map<String, Object> params){
        PageUtils page = systemRoleService.queryPage(params);
        return ResultUtils.ResultOk().put("page", page);
    }

    /**
     * 角色列表
     */
    @GetMapping("/select")
    @RequiresPermissions("sys:role:select")
    @ApiOperation("角色列表")
    public ResultUtils select(){
        List<SystemRole> list = systemRoleService.list();
        return ResultUtils.ResultOk().put("list", list);
    }

    /**
     * 角色信息
     */
    @GetMapping("/info/{roleId}")
    @RequiresPermissions("sys:role:info")
    @ApiOperation("角色信息")
    public ResultUtils info(@PathVariable("roleId") Long roleId){
        SystemRole role = systemRoleService.getById(roleId);
        //查询角色对应的菜单
        List<Long> menuIdList = systemRoleMenuService.queryMenuIdList(roleId);
        role.setMenuIdList(menuIdList);
        //查询角色对应的部门
        List<Long> deptIdList = systemRoleDeptService.queryDeptIdList(new Long[]{roleId});
        role.setDeptIdList(deptIdList);
        return ResultUtils.ResultOk().put("role", role);
    }

    /**
     * 保存角色
     */
    @PostMapping("/save")
    @RequiresPermissions("sys:role:save")
    @ApiOperation("角色保存")
    public ResultUtils save(@RequestBody SystemRole role){
//        ValidatorUtils.validateEntity(role);
        systemRoleService.saveRole(role);
        return ResultUtils.ResultOk();
    }

    /**
     * 修改角色
     */
    @PostMapping("/update")
    @RequiresPermissions("sys:role:update")
    @ApiOperation("角色更新")
    public ResultUtils update(@RequestBody SystemRole role){
//        ValidatorUtils.validateEntity(role);
        systemRoleService.update(role);
        return ResultUtils.ResultOk();
    }

    /**
     * 删除角色
     */
    @DeleteMapping("/delete")
    @RequiresPermissions("sys:role:delete")
    @ApiOperation("角色删除")
    public ResultUtils delete(@RequestBody Long[] roleIds){
        systemRoleService.deleteBatch(roleIds);
        return ResultUtils.ResultOk();
    }
}



