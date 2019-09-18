package com.example.demomybatis.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demomybatis.system.entity.SystemDept;
import com.example.demomybatis.system.entity.SystemRole;
import com.example.demomybatis.system.mapper.SystemRoleMapper;
import com.example.demomybatis.system.service.*;
import com.example.demomybatis.utils.ConstantUtils;
import com.example.demomybatis.utils.PageUtils;
import com.example.demomybatis.utils.QueryUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * @liuhongchun
 * @Date 2019/9/15  19:41
 * @des
 */
@Service
public class SystemRoleServiceImpl extends ServiceImpl<SystemRoleMapper, SystemRole> implements SystemRoleService {

    @Autowired
    private SystemRoleMenuService systemRoleMenuService;
    @Autowired
    private SystemRoleDeptService systemRoleDeptService;
    @Autowired
    private SystemUserRoleService systemUserRoleService;
    @Autowired
    private SystemDeptService systemDeptService;

    @Override
    // @DataFilter(subDept = true, user = false)
    public PageUtils queryPage(Map<String, Object> params) {
        String roleName = (String) params.get("roleName");

        IPage<SystemRole> page = this.page(
                new QueryUtils<SystemRole>().getPage(params),
                new QueryWrapper<SystemRole>()
                        .like(StringUtils.isNotBlank(roleName), "role_name", roleName)
                        .apply(params.get(ConstantUtils.SQL_FILTER) != null, (String) params.get(ConstantUtils.SQL_FILTER))
        );

        for (SystemRole systemRole : page.getRecords()) {
            SystemDept systemDept = systemDeptService.getById(systemRole.getDeptId());
            if (systemDept != null) {
                systemRole.setDeptName(systemDept.getName());
            }
        }

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRole(SystemRole role) {
        role.setCreateTime(new Date());
        this.save(role);

        //保存角色与菜单关系
        systemRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());

        //保存角色与部门关系
        systemRoleDeptService.saveOrUpdate(role.getRoleId(), role.getDeptIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SystemRole role) {
        this.updateById(role);

        //更新角色与菜单关系
        systemRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());

        //保存角色与部门关系
        systemRoleDeptService.saveOrUpdate(role.getRoleId(), role.getDeptIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除角色
        this.removeByIds(Arrays.asList(roleIds));

        //删除角色与菜单关联
        systemRoleMenuService.deleteBatch(roleIds);

        //删除角色与部门关联
        systemRoleDeptService.deleteBatch(roleIds);

        //删除角色与用户关联
        systemUserRoleService.deleteBatch(roleIds);
    }

}
