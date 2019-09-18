package com.example.demomybatis.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demomybatis.system.entity.SystemDept;
import com.example.demomybatis.system.entity.SystemLog;
import com.example.demomybatis.system.mapper.SystemDeptMapper;
import com.example.demomybatis.system.mapper.SystemLogMapper;
import com.example.demomybatis.system.service.SystemDeptService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @liuhongchun
 * @Date 2019/9/15  19:42
 * @des
 */
@Service
public class SystemDeptServiceImpl extends ServiceImpl<SystemDeptMapper, SystemDept> implements SystemDeptService {

    @Override
    //@DataFilter(subDept = true, user = false, tableAlias = "t1")
    public List<SystemDept> queryList(Map<String, Object> params){
        return baseMapper.queryList(params);
    }

    @Override
    public List<Long> queryDetpIdList(Long parentId) {
        return baseMapper.queryDetpIdList(parentId);
    }

    @Override
    public List<Long> getSubDeptIdList(Long deptId){
        //部门及子部门ID列表
        List<Long> deptIdList = new ArrayList<>();

        //获取子部门ID
        List<Long> subIdList = queryDetpIdList(deptId);
        getDeptTreeList(subIdList, deptIdList);

        return deptIdList;
    }

    /**
     * 递归
     */
    private void getDeptTreeList(List<Long> subIdList, List<Long> deptIdList){
        for(Long deptId : subIdList){
            List<Long> list = queryDetpIdList(deptId);
            if(list.size() > 0){
                getDeptTreeList(list, deptIdList);
            }

            deptIdList.add(deptId);
        }
    }
}
