package com.example.demomybatis.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demomybatis.system.entity.SystemUserRole;
import com.example.demomybatis.system.provider.SystemUserRoleSql;
import com.example.demomybatis.system.provider.SystemUserSql;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @liuhongchun
 * @Date 2019/9/15  18:24
 */
@Mapper
public interface SystemUserRoleMapper extends BaseMapper<SystemUserRole> {

    /**
     * 根据用户ID，获取角色ID列表
     */
    @SelectProvider(type = SystemUserRoleSql.class ,method = "queryRoleIdList")
    List<Long> queryRoleIdList(Long userId);

    /**
     * 根据角色ID数组，批量删除
     */
    @SelectProvider(type = SystemUserRoleSql.class ,method = "deleteBatch")
    int deleteBatch(Long[] roleIds);
}
