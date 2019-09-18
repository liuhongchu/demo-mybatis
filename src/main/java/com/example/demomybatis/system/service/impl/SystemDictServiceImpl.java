package com.example.demomybatis.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demomybatis.system.entity.SystemDept;
import com.example.demomybatis.system.entity.SystemDict;
import com.example.demomybatis.system.mapper.SystemDeptMapper;
import com.example.demomybatis.system.mapper.SystemDictMapper;
import com.example.demomybatis.system.service.SystemDeptService;
import com.example.demomybatis.system.service.SystemDictService;
import com.example.demomybatis.utils.PageUtils;
import com.example.demomybatis.utils.QueryUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @liuhongchun
 * @Date 2019/9/17  11:04
 * @des
 */
@Service
public class SystemDictServiceImpl extends ServiceImpl<SystemDictMapper, SystemDict> implements SystemDictService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String name = (String)params.get("name");

        IPage<SystemDict> page = this.page(
                new QueryUtils<SystemDict>().getPage(params),
                new QueryWrapper<SystemDict>()
                        .like(StringUtils.isNotBlank(name),"name", name)
        );

        return new PageUtils(page);
    }
}
