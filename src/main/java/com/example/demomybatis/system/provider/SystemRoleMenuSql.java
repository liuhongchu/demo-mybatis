package com.example.demomybatis.system.provider;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

/**
 * @liuhongchun
 * @Date 2019/9/16  10:02
 * @des
 */
public class SystemRoleMenuSql implements ProviderMethodResolver {
    /**
     * 根据角色ID，获取菜单ID列表
     * @param roleId
     * @return
     */
    public  String queryMenuIdList(Long roleId) {
        return new SQL() {{
            SELECT("menu_id");
            FROM("system_role_menu");
            WHERE("role_id = #{value}");
        }}.toString();
    }
    /**
     * 根据角色ID数组，批量删除
     * @param roleIds
     * @return
     */
    public  String deleteBatch(Long[] roleIds) {
        return new SQL() {{
            for (Long roleId : roleIds) {
                DELETE_FROM("system_role_menu");
                WHERE("role_id in #{roleId}");
            }
        }}.toString();
    }
}
