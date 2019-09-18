package com.example.demomybatis.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demomybatis.system.entity.SystemMenu;
import com.example.demomybatis.system.entity.SystemRoleMenu;
import com.example.demomybatis.system.mapper.SystemMenuMapper;
import com.example.demomybatis.system.service.SystemMenuService;
import com.example.demomybatis.system.service.SystemRoleMenuService;
import com.example.demomybatis.system.service.SystemUserService;
import com.example.demomybatis.utils.ConstantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @liuhongchun
 * @Date 2019/9/15  19:42
 * @des
 */
@Service
public class SystemMenuServiceImpl extends ServiceImpl<SystemMenuMapper,SystemMenu> implements SystemMenuService {

    @Autowired
    private SystemUserService systemUserService; 
    @Autowired
    private SystemRoleMenuService  systemRoleMenuService; 

    @Override
    public List<SystemMenu> queryListParentId(Long parentId, List<Long> menuIdList) {
        List<SystemMenu> menuList = queryListParentId(parentId);
        if(menuIdList == null){
            return menuList;
        }

        List<SystemMenu> userMenuList = new ArrayList<>();
        for(SystemMenu menu : menuList){
            if(menuIdList.contains(menu.getMenuId())){
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public List<SystemMenu> queryListParentId(Long parentId) {
        return baseMapper.queryListParentId(parentId);
    }

    @Override
    public List<SystemMenu> queryNotButtonList() {
        return baseMapper.queryNotButtonList();
    }

    @Override
    public List<SystemMenu> getUserMenuList(Long userId) {
        //系统管理员，拥有最高权限
        if(userId == ConstantUtils.SUPER_ADMIN){
            return getAllMenuList(null);
        }

        //用户菜单列表
        List<Long> menuIdList = systemUserService.queryAllMenuId(userId);
        return getAllMenuList(menuIdList);
    }

    @Override
    public void delete(Long menuId){
        //删除菜单
        this.removeById(menuId);
        //删除菜单与角色关联
        systemRoleMenuService.remove(new QueryWrapper<SystemRoleMenu>().eq("menu_id", menuId));
    }

    /**
     * 获取所有菜单列表
     */
    private List<SystemMenu> getAllMenuList(List<Long> menuIdList){
        //查询根菜单列表
        List<SystemMenu> menuList = queryListParentId(0L, menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }

    /**
     * 递归
     */
    private List<SystemMenu> getMenuTreeList(List<SystemMenu> menuList, List<Long> menuIdList){
        List<SystemMenu> subMenuList = new ArrayList<SystemMenu>();

        for(SystemMenu entity : menuList){
            //目录
            if(entity.getType() == ConstantUtils.MenuType.CATALOG.getValue()){
                entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }

        return subMenuList;
    }
}
