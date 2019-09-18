package com.example.demomybatis.system.provider;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;
/**
 * @liuhongchun
 * @Date 2019/9/16  9:57
 * @des
 */
public class SystemUserSql implements ProviderMethodResolver {
    /**
     * 查询用户的所有权限
     * @param userId
     * @return String
     */
    public  String queryAllPerms(Long userId) {
        return new SQL()
            .SELECT("m.perms")
            .FROM("system_user_role ur")
            .LEFT_OUTER_JOIN("system_role_menu rm  on ur.role_id = rm.role_id")
            .LEFT_OUTER_JOIN("system_menu m  on rm.menu_id = m.menu_id")
            .WHERE("ur.user_id = #{"+userId+"}")
        .toString();
    }

    /**
     * 查询用户的所有菜单ID
     * @param userId
     * @return String
     */
    public  String queryAllMenuId(Long userId) {
        return new SQL(){{
            SELECT_DISTINCT("rm.menu_id");
                FROM ("system_user_role ur ");
                LEFT_OUTER_JOIN ("system_role_menu rm on ur.role_id = rm.role_id ");
                WHERE ("ur.user_id = #{"+userId+"}");
        }}.toString();
    }
}
