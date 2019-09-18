package com.example.demomybatis.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demomybatis.system.entity.SystemDict;
import com.example.demomybatis.utils.PageUtils;

import java.util.Map;

/**
 * @liuhongchun
 * @Date 2019/9/15  19:09
 * @des 数据字典
 */
public interface SystemDictService extends IService<SystemDict> {

    PageUtils queryPage(Map<String, Object> params);
}

