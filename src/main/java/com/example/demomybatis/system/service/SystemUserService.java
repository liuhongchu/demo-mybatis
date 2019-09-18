package com.example.demomybatis.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demomybatis.system.entity.SystemUser;
import com.example.demomybatis.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * @liuhongchun
 * @Date 2019/9/15  19:09
 * @des 系统用户
 */
public interface SystemUserService extends IService<SystemUser> {

	PageUtils queryPage(Map<String, Object> params);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);
	
	/**
	 * 保存用户
	 */
	void saveUser(SystemUser user);
	
	/**
	 * 修改用户
	 */
	void update(SystemUser user);

	/**
	 * 修改密码
	 * @param userId       用户ID
	 * @param password     原密码
	 * @param newPassword  新密码
	 */
	boolean updatePassword(Long userId, String password, String newPassword);
}
