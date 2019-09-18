package com.example.demomybatis.system.provider;

import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * @liuhongchun
 * @Date 2019/9/16  9:51
 * @des
 */
public class SystemRoleDeptSql implements ProviderMethodResolver {

    /**
     * 根据角色ID，获取部门ID列表
     * @param roleIds
     * @return
     */
    public  String  queryDeptIdList(Long[] roleIds){
        return new SQL(){{
            for (Long roleId:roleIds) {
                SELECT ("dept_id");
                FROM ("system_role_dept");
                WHERE ("role_id in #{"+roleId+"}");
            }
        }}.toString();
    }
    /**
     * 根据角色ID数组，批量删除
     * @param roleIds
     * @return
     */
    public  String  deleteBatch(Long[] roleIds){
        return new SQL(){{
            for (Long roleId: roleIds) {
                DELETE_FROM(" system_role_dept") ;
                WHERE ("role_id in #{"+roleId+"}");
            }
        }}.toString();
    }
}
