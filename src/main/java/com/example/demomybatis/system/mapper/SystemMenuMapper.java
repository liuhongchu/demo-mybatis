package com.example.demomybatis.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demomybatis.system.entity.SystemMenu;
import com.example.demomybatis.system.provider.SystemMenuSql;
import com.example.demomybatis.system.provider.SystemRoleDeptSql;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @liuhongchun
 * @Date 2019/9/15  18:23
 */
@Mapper
public interface SystemMenuMapper extends BaseMapper<SystemMenu> {
    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    @SelectProvider(type = SystemMenuSql.class ,method = "queryListParentId")
    List<SystemMenu> queryListParentId(Long parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    @SelectProvider(type = SystemMenuSql.class ,method = "queryNotButtonList")
    List<SystemMenu> queryNotButtonList();
}
