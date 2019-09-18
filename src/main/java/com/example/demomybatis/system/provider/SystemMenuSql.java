package com.example.demomybatis.system.provider;
import com.example.demomybatis.system.entity.SystemMenu;
import com.example.demomybatis.system.entity.SystemUser;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * @liuhongchun
 * @Date 2019/9/16  8:57
 * @des
 */
public class SystemMenuSql implements ProviderMethodResolver {

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    public  String  queryListParentId(Long parentId){
        return new SQL(){{
            SELECT(" *");
            FROM ("system_menu");
            WHERE ("parent_id = #{"+parentId+"}");
            ORDER_BY("order_num asc");
        }}.toString();
    }

    /**
     * 获取不包含按钮的菜单列表
     */
    public  String  queryNotButtonList(){
        return new SQL(){{
            SELECT ("*") ;
            FROM ("system_menu");
            WHERE ("type != 2");
            ORDER_BY ("order_num asc");
        }}.toString();
    }

}
