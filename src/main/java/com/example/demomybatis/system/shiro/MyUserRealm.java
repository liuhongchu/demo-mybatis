package com.example.demomybatis.system.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demomybatis.system.entity.SystemMenu;
import com.example.demomybatis.system.entity.SystemUser;
import com.example.demomybatis.system.mapper.SystemMenuMapper;
import com.example.demomybatis.system.mapper.SystemUserMapper;
import com.example.demomybatis.utils.ConstantUtils;
import com.example.demomybatis.utils.ShiroUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @liuhongchun
 * @Date 2019/9/16  12:12
 * @des
 */
@SuppressWarnings("ALL")
@Component
public class MyUserRealm extends AuthorizingRealm {
    @Autowired
    private SystemUserMapper systemUserMapper;

    @Autowired
    private SystemMenuMapper systemMenuMapper;
    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SystemUser user = (SystemUser) principals.getPrimaryPrincipal();
        Long userId = user.getUserId();

        List<String> permsList;

        //系统管理员，拥有最高权限
        if (userId == ConstantUtils.SUPER_ADMIN) {
            List<SystemMenu> menuList = systemMenuMapper.selectList(null);
            permsList = new ArrayList<>(menuList.size());
            for (SystemMenu menu : menuList) {
                permsList.add(menu.getPerms());
            }
        } else {
            permsList = systemUserMapper.queryAllPerms(userId);
        }

        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for (String perms : permsList) {
            if (StringUtils.isBlank(perms)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

        QueryWrapper queryWrapper =  new QueryWrapper<SystemUser>().eq("user_name", token.getUsername());

        //查询用户信息
        SystemUser user = systemUserMapper.selectOne(queryWrapper);
        //账号不存在
        if (user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }

        //账号锁定
        if (user.getStatus() == 0) {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassWord(), ByteSource.Util.bytes(user.getSalt()), getName());
        return info;
    }

    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
        shaCredentialsMatcher.setHashAlgorithmName(ShiroUtils.hashAlgorithmName);
        shaCredentialsMatcher.setHashIterations(ShiroUtils.hashIterations);
        super.setCredentialsMatcher(shaCredentialsMatcher);
    }
}
