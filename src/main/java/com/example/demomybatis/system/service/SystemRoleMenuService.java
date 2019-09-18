package com.example.demomybatis.system.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demomybatis.system.entity.SystemRoleMenu;

import java.util.List;
/**
 * @liuhongchun
 * @Date 2019/9/15
 * @des 角色与菜单对应关系
 */
public interface SystemRoleMenuService extends IService<SystemRoleMenu> {
	
	 void saveOrUpdate(Long roleId, List<Long> menuIdList);
	
	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<Long> queryMenuIdList(Long roleId);

	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(Long[] roleIds);
	
}
