package com.example.demomybatis.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demomybatis.system.entity.SystemRoleMenu;
import com.example.demomybatis.system.provider.SystemRoleMenuSql;
import com.example.demomybatis.system.provider.SystemUserRoleSql;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @liuhongchun
 * @Date 2019/9/15  18:24
 */
@Mapper
public interface SystemRoleMenuMapper extends BaseMapper<SystemRoleMenu> {
    /**
     * 根据角色ID，获取菜单ID列表
     */
    @SelectProvider(type = SystemRoleMenuSql.class ,method = "queryMenuIdList")
    List<Long> queryMenuIdList(Long roleId);

    /**
     * 根据角色ID数组，批量删除
     */
    @SelectProvider(type = SystemRoleMenuSql.class ,method = "deleteBatch")
    int deleteBatch(Long[] roleIds);
}
