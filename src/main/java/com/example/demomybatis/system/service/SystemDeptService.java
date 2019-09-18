package com.example.demomybatis.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demomybatis.system.entity.SystemDept;

import java.util.List;
import java.util.Map;

/**
 * @liuhongchun
 * @Date 2019/9/15
 * @des 部门管理
 */
public interface SystemDeptService extends IService<SystemDept> {

	List<SystemDept> queryList(Map<String, Object> map);

	/**
	 * 查询子部门ID列表
	 * @param parentId  上级部门ID
	 */
	List<Long> queryDetpIdList(Long parentId);

	/**
	 * 获取子部门ID，用于数据过滤
	 */
	List<Long> getSubDeptIdList(Long deptId);

}
