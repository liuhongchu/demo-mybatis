package com.example.demomybatis.system.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demomybatis.system.entity.SystemConfig;
import com.example.demomybatis.system.mapper.SystemConfigMapper;
import com.example.demomybatis.system.service.SystemConfigService;
import com.example.demomybatis.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @liuhongchun
 * @Date 2019/9/15  19:43
 * @des
 */
@Service
public class SystemConfigServiceImpl extends ServiceImpl<SystemConfigMapper, SystemConfig> implements SystemConfigService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public void saveConfig(SystemConfig config) {

    }

    @Override
    public void update(SystemConfig config) {

    }

    @Override
    public void updateValueByKey(String key, String value) {

    }

    @Override
    public void deleteBatch(Long[] ids) {

    }

    @Override
    public String getValue(String key) {
        return null;
    }

    @Override
    public <T> T getConfigObject(String key, Class<T> clazz) {
        return null;
    }
}
