package com.example.demomybatis.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demomybatis.system.entity.SystemRoleDept;

import java.util.List;

/**
 * @liuhongchun
 * @Date 2019/9/15  19:09
 * @des 角色与部门对应关系
 */
public interface SystemRoleDeptService extends IService<SystemRoleDept> {
	
	void saveOrUpdate(Long roleId, List<Long> deptIdList);
	
	/**
	 * 根据角色ID，获取部门ID列表
	 */
	List<Long> queryDeptIdList(Long[] roleIds) ;

	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(Long[] roleIds);
}
