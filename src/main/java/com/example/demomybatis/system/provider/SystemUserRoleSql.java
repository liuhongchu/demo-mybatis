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
public class SystemUserRoleSql implements ProviderMethodResolver {

    /**
     * 根据用户ID，获取角色ID列表
     * @param userId
     * @return
     */
     public  String queryRoleIdList(Long userId){
        return new SQL(){{
            SELECT ("role_id") ;
            FROM ("system_user_role");
            WHERE ("user_id = #{"+userId+"}");
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
                DELETE_FROM ("system_user_role");
                WHERE("role_id in  #{"+roleId+"}");
            }
        }}.toString();
    }
}
