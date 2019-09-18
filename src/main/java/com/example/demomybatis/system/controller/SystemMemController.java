package com.example.demomybatis.system.controller;

import com.example.demomybatis.exception.CustomException;
import com.example.demomybatis.system.entity.SystemMenu;
import com.example.demomybatis.system.service.SystemMenuService;
import com.example.demomybatis.utils.ConstantUtils;
import com.example.demomybatis.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @liuhongchun
 * @Date 2019/9/15  17:01
 */
@RestController
@RequestMapping("/system/mem")
@Slf4j
@Api(tags="系统菜单接口")
public class SystemMemController extends BaseController{
    @Autowired
    private SystemMenuService systemMenuService;

    /**
     * 导航菜单
     */
    @GetMapping("/nav")
    @ApiOperation("菜单导航")
    public ResultUtils nav(){
        List<SystemMenu> menuList = systemMenuService.getUserMenuList(getUserId());
        return ResultUtils.ResultOk().put("menuList", menuList);
    }

    /**
     * 所有菜单列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:menu:list")
    @ApiOperation("所有菜单列表")
    public List<SystemMenu> list(){
        List<SystemMenu> menuList = systemMenuService.list();
        for(SystemMenu sysMenuEntity : menuList){
            SystemMenu parentMenuEntity = systemMenuService.getById(sysMenuEntity.getParentId());
            if(parentMenuEntity != null){
                sysMenuEntity.setParentName(parentMenuEntity.getName());
            }
        }

        return menuList;
    }

    /**
     * 选择菜单(添加、修改菜单)
     */
    @GetMapping("/select")
    @RequiresPermissions("sys:menu:select")
    @ApiOperation("菜单增加/修改")
    public ResultUtils select(){
        //查询列表数据
        List<SystemMenu> menuList = systemMenuService.queryNotButtonList();
        //添加顶级菜单
        SystemMenu root = new SystemMenu();
        root.setMenuId(0L);
        root.setName("一级菜单");
        root.setParentId(-1L);
        root.setOpen(true);
        menuList.add(root);
        return ResultUtils.ResultOk().put("menuList", menuList);
    }

    /**
     * 菜单信息
     */
    @GetMapping("/info/{menuId}")
    @RequiresPermissions("sys:menu:info")
    @ApiOperation("菜单信息")
    public ResultUtils info(@PathVariable("menuId") Long menuId){
        SystemMenu menu = systemMenuService.getById(menuId);
        return ResultUtils.ResultOk().put("menu", menu);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("sys:menu:save")
    @ApiOperation("菜单保存")
    public ResultUtils save(@RequestBody SystemMenu menu){
        //数据校验
        verifyForm(menu);
        systemMenuService.save(menu);

        return ResultUtils.ResultOk();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("sys:menu:update")
    @ApiOperation("菜单修改")
    public ResultUtils update(@RequestBody SystemMenu menu){
        //数据校验
        verifyForm(menu);
        systemMenuService.updateById(menu);
        return ResultUtils.ResultOk();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @RequiresPermissions("sys:menu:delete")
    @ApiOperation("菜单删除")
    public ResultUtils delete(long menuId){
        if(menuId <= 31){
            return ResultUtils.ResultError("系统菜单，不能删除");
        }

        //判断是否有子菜单或按钮
        List<SystemMenu> menuList = systemMenuService.queryListParentId(menuId);
        if(menuList.size() > 0){
            return ResultUtils.ResultError("请先删除子菜单或按钮");
        }

        systemMenuService.delete(menuId);

        return ResultUtils.ResultOk();
    }

    /**
     * 验证参数是否正确
     */
    private void verifyForm(SystemMenu menu){
        if(StringUtils.isBlank(menu.getName())){
            throw new CustomException("菜单名称不能为空");
        }

        if(menu.getParentId() == null){
            throw new CustomException("上级菜单不能为空");
        }

        //菜单
        if(menu.getType() == ConstantUtils.MenuType.MENU.getValue()){
            if(StringUtils.isBlank(menu.getUrl())){
                throw new CustomException("菜单URL不能为空");
            }
        }

        //上级菜单类型
        int parentType = ConstantUtils.MenuType.CATALOG.getValue();
        if(menu.getParentId() != 0){
            SystemMenu parentMenu = systemMenuService.getById(menu.getParentId());
            parentType = parentMenu.getType();
        }

        //目录、菜单
        if(menu.getType() == ConstantUtils.MenuType.CATALOG.getValue() ||
                menu.getType() == ConstantUtils.MenuType.MENU.getValue()){
            if(parentType != ConstantUtils.MenuType.CATALOG.getValue()){
                throw new CustomException("上级菜单只能为目录类型");
            }
            return ;
        }

        //按钮
        if(menu.getType() == ConstantUtils.MenuType.BUTTON.getValue()){
            if(parentType != ConstantUtils.MenuType.MENU.getValue()){
                throw new CustomException("上级菜单只能为菜单类型");
            }
            return ;
        }
    }
}
