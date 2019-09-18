package com.example.demomybatis.system.provider;

import com.example.demomybatis.system.entity.SystemConfig;
import com.example.demomybatis.system.entity.SystemUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

/**
 * @liuhongchun
 * @Date 2019/9/16  8:57
 * @des
 */
public class SystemConfigSql implements ProviderMethodResolver {

    /**
     * 根据key，查询value
     * @param paramKey
     * @return
     */
    public  String queryByKey(String paramKey) {
        return new SQL() {{
            SELECT("*");
            FROM("sys_config");
            WHERE("param_key = #{"+paramKey+"}");
        }}.toString();
    }
    /**
     * 根据key，更新value
     * @param paramKey
     * @param paramValue
     * @return
     */
    public  String updateValueByKey(@Param("paramKey") String paramKey, @Param("paramValue") String paramValue) {
        return new SQL() {{
            UPDATE("sys_config");
            SET("param_value = #{"+paramValue+"}");
            WHERE(" param_key = #{"+paramKey+"}");
        }}.toString();
    }

}
