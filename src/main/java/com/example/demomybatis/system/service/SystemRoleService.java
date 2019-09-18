package com.example.demomybatis.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demomybatis.system.entity.SystemRole;
import com.example.demomybatis.utils.PageUtils;

import java.util.Map;

/**
 * @liuhongchun
 * @Date 2019/9/15  15:09
 * @des 角色
 */

public interface SystemRoleService extends IService<SystemRole> {

	PageUtils queryPage(Map<String, Object> params);

	void saveRole(SystemRole role);

	void update(SystemRole role);
	
	void deleteBatch(Long[] roleIds);

}
