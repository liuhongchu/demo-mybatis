package com.example.demomybatis.system.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demomybatis.system.entity.SystemUser;
import com.example.demomybatis.system.provider.SystemUserSql;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import java.util.List;

@Mapper
public interface SystemUserMapper extends BaseMapper<SystemUser> {

    /**
     * 查询用户的所有权限
     * @param userId  用户ID
     */
    @SelectProvider(type = SystemUserSql.class ,method = "queryAllPerms")
    List<String> queryAllPerms(Long userId);

    /**
     * 查询用户的所有菜单ID
     */
    @SelectProvider(type = SystemUserSql.class ,method = "queryAllMenuId" )
    List<Long> queryAllMenuId(Long userId);
}
