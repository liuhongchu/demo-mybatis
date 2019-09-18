package com.example.demomybatis.system.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demomybatis.system.entity.SystemLog;
import com.example.demomybatis.utils.PageUtils;

import java.util.Map;

/**
 * @liuhongchun
 * @Date 2019/9/15
 * @des 系统日志
 */

public interface SystemLogService extends IService<SystemLog> {

    PageUtils queryPage(Map<String, Object> params);

}
