package com.example.demomybatis.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demomybatis.system.entity.SystemRoleDept;
import com.example.demomybatis.system.provider.SystemRoleDeptSql;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @liuhongchun
 * @Date 2019/9/15  18:23
 */
@Mapper
public interface SystemRoleDeptMapper extends BaseMapper<SystemRoleDept> {

    /**
     * 根据角色ID，获取部门ID列表
     */
    @SelectProvider(type = SystemRoleDeptSql.class ,method = "queryDeptIdList")
    List<Long> queryDeptIdList(Long[] roleIds);

    /**
     * 根据角色ID数组，批量删除
     */
    @SelectProvider(type = SystemRoleDeptSql.class ,method = "deleteBatch")
    int deleteBatch(Long[] roleIds);
}
