package com.example.demomybatis.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demomybatis.system.entity.SystemConfig;
import com.example.demomybatis.system.provider.SystemConfigSql;
import com.example.demomybatis.system.provider.SystemDeptSql;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

/**
 * @liuhongchun
 * @Date 2019/9/15  18:22
 */
@Mapper
public interface SystemConfigMapper extends BaseMapper<SystemConfig> {

    /**
     * 根据key，查询value
     */
    @SelectProvider(type = SystemConfigSql.class ,method = "queryByKey")
    SystemConfig queryByKey(String paramKey);

    /**
     * 根据key，更新value
     */
    @SelectProvider(type = SystemConfigSql.class ,method = "updateValueByKey")
    int updateValueByKey(@Param("paramKey") String paramKey, @Param("paramValue") String paramValue);

}
