package com.example.demomybatis.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demomybatis.system.entity.SystemRole;
import com.example.demomybatis.system.entity.SystemRoleMenu;
import com.example.demomybatis.system.mapper.SystemRoleMapper;
import com.example.demomybatis.system.mapper.SystemRoleMenuMapper;
import com.example.demomybatis.system.service.SystemRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @liuhongchun
 * @Date 2019/9/15  19:41
 * @des
 */
@Service
public class SystemRoleMenuServiceImpl extends ServiceImpl<SystemRoleMenuMapper, SystemRoleMenu> implements SystemRoleMenuService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
        //先删除角色与菜单关系
        deleteBatch(new Long[]{roleId});

        if(menuIdList.size() == 0){
            return ;
        }

        //保存角色与菜单关系
        for(Long menuId : menuIdList){
            SystemRoleMenu systemRoleMenu = new SystemRoleMenu();
            systemRoleMenu.setMenuId(menuId);
            systemRoleMenu.setRoleId(roleId);

            this.save(systemRoleMenu);
        }
    }

    @Override
    public List<Long> queryMenuIdList(Long roleId) {
        return baseMapper.queryMenuIdList(roleId);
    }

    @Override
    public int deleteBatch(Long[] roleIds){
        return baseMapper.deleteBatch(roleIds);
    }
}
