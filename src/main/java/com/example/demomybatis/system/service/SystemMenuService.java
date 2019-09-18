package com.example.demomybatis.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demomybatis.system.entity.SystemMenu;

import java.util.List;
/**
 * @liuhongchun
 * @Date 2019/9/15  12:09
 * @des 系统菜单
 */
public interface SystemMenuService extends IService<SystemMenu> {

	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 * @param menuIdList  用户菜单ID
	 */
	List<SystemMenu> queryListParentId(Long parentId, List<Long> menuIdList);

	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 */
	List<SystemMenu> queryListParentId(Long parentId);
	
	/**
	 * 获取不包含按钮的菜单列表
	 */
	List<SystemMenu> queryNotButtonList();
	
	/**
	 * 获取用户菜单列表
	 */
	List<SystemMenu> getUserMenuList(Long userId);

	/**
	 * 删除
	 */
	void delete(Long menuId);
}
