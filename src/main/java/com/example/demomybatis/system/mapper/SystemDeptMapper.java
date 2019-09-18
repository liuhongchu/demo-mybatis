package com.example.demomybatis.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demomybatis.system.entity.SystemDept;
import com.example.demomybatis.system.provider.SystemDeptSql;
import com.example.demomybatis.system.provider.SystemMenuSql;
import org.apache.ibatis.annotations.SelectProvider;
import org.mapstruct.Mapper;
import java.util.List;
import java.util.Map;

/**
 * @liuhongchun
 * @Date 2019/9/15  18:22
 */
@Mapper
public interface SystemDeptMapper extends BaseMapper<SystemDept> {

    @SelectProvider(type = SystemDeptSql.class ,method = "queryList")
    List<SystemDept> queryList(Map<String, Object> params);

    /**
     * 查询子部门ID列表
     * @param parentId  上级部门ID
     */
    @SelectProvider(type = SystemDeptSql.class ,method = "queryDetpIdList")
    List<Long> queryDetpIdList(Long parentId);

}

