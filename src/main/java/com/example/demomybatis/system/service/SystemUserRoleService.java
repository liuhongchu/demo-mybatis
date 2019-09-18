package com.example.demomybatis.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demomybatis.system.entity.SystemUserRole;

import java.util.List;

/**
 * @liuhongchun
 * @Date 2019/9/15  15:09
 * @des 用户与角色对应关系
 */
public interface SystemUserRoleService extends IService<SystemUserRole> {
	
	void saveOrUpdate(Long userId, List<Long> roleIdList);
	
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Long> queryRoleIdList(Long userId);

	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(Long[] roleIds);
}
