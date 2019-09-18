package com.example.demomybatis.system.provider;

import com.example.demomybatis.system.entity.SystemDept;
import com.example.demomybatis.utils.ConstantUtils;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

/**
 * @liuhongchun
 * @Date 2019/9/16  8:50
 * @des
 */
public class SystemDeptSql implements ProviderMethodResolver {


    public  String queryList(Map<String, Object> params) {
        return new SQL() {{
            SELECT("t1.*,(select t2.name from system_dept t2 where t2.dept_id=t1.parent_id) parentName ");
            FROM("system_dept t1");
            WHERE("t1.del_flag = 0");
            //            if (ConstantUtils.SQL_FILTER != null){
            //                AND();
            //            }
        }}.toString();
    }

    /**
     * 查询子部门ID列表
     *
     * @param parentId 上级部门ID
     */
    public  String queryDetpIdList(Long parentId) {
        return new SQL() {{
            SELECT("dept_id ");
            FROM("system_dept");
            WHERE("parent_id = #{" + parentId + "} and del_flag = 0");
        }}.toString();
    }

}
