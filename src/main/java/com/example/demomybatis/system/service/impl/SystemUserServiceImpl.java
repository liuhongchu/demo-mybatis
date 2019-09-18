package com.example.demomybatis.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demomybatis.system.entity.SystemDept;
import com.example.demomybatis.system.entity.SystemUser;
import com.example.demomybatis.system.mapper.SystemUserMapper;
import com.example.demomybatis.system.service.SystemDeptService;
import com.example.demomybatis.system.service.SystemUserRoleService;
import com.example.demomybatis.system.service.SystemUserService;
import com.example.demomybatis.utils.ConstantUtils;
import com.example.demomybatis.utils.PageUtils;
import com.example.demomybatis.utils.QueryUtils;
import com.example.demomybatis.utils.ShiroUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @liuhongchun
 * @Date 2019/9/15  19:40
 * @des
 */
@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper,SystemUser> implements SystemUserService {

    @Autowired
    private SystemUserRoleService systemUserRoleService;
    @Autowired
    private SystemDeptService systemDeptService;

    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return baseMapper.queryAllMenuId(userId);
    }

    @Override
//    @DataFilter(subDept = true, user = false)
    public PageUtils queryPage(Map<String, Object> params) {
        String username = (String)params.get("username");

        IPage<SystemUser> page = this.page(
                new QueryUtils<SystemUser>().getPage(params),
                new QueryWrapper<SystemUser>()
                        .like(StringUtils.isNotBlank(username),"username", username)
                        .apply(params.get(ConstantUtils.SQL_FILTER) != null, (String)params.get(ConstantUtils.SQL_FILTER))
        );

        for(SystemUser systemUser : page.getRecords()){
            SystemDept systemDept = systemDeptService.getById(systemUser.getDeptId());
            systemUser.setDeptName(systemDept.getName());
        }

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUser(SystemUser user) {
        user.setCreateTime(new Date());
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        user.setSalt(salt);
        user.setPassWord(ShiroUtils.sha256(user.getPassWord(), user.getSalt()));
        this.save(user);

        //保存用户与角色关系
        systemUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SystemUser user) {
        if(StringUtils.isBlank(user.getPassWord())){
            user.setPassWord(null);
        }else{
            SystemUser userEntity = this.getById(user.getUserId());
            user.setPassWord(ShiroUtils.sha256(user.getPassWord(), userEntity.getSalt()));
        }
        this.updateById(user);

        //保存用户与角色关系
        systemUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }


    @Override
    public boolean updatePassword(Long userId, String password, String newPassword) {
        SystemUser systemUser = new SystemUser();
        systemUser.setPassWord(newPassword);
        return this.update(systemUser,
                new QueryWrapper<SystemUser>().eq("user_id", userId).eq("password", password));
    }
}
