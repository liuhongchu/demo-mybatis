package com.example.demomybatis.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demomybatis.system.entity.SystemRoleDept;
import com.example.demomybatis.system.mapper.SystemRoleDeptMapper;
import com.example.demomybatis.system.service.SystemRoleDeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @liuhongchun
 * @Date 2019/9/15  19:42
 * @des
 */
@Service
public class SystemRoleDeptServiceImpl extends ServiceImpl<SystemRoleDeptMapper, SystemRoleDept> implements SystemRoleDeptService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Long roleId, List<Long> deptIdList) {
        //先删除角色与部门关系
        deleteBatch(new Long[]{roleId});

        if (deptIdList.size() == 0) {
            return;
        }

        //保存角色与菜单关系
        for (Long deptId : deptIdList) {
            SystemRoleDept systemRoleDept = new SystemRoleDept();
            systemRoleDept.setDeptId(deptId);
            systemRoleDept.setRoleId(roleId);

            this.save(systemRoleDept);
        }
    }

    @Override
    public List<Long> queryDeptIdList(Long[] roleIds) {
        return baseMapper.queryDeptIdList(roleIds);
    }

    @Override
    public int deleteBatch(Long[] roleIds) {
        return baseMapper.deleteBatch(roleIds);
    }
}
