package com.example.demomybatis.system.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demomybatis.system.entity.SystemLog;
import com.example.demomybatis.system.mapper.SystemLogMapper;
import com.example.demomybatis.system.service.SystemLogService;
import com.example.demomybatis.utils.PageUtils;
import com.example.demomybatis.utils.QueryUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * @liuhongchun
 * @Date 2019/9/15  19:42
 * @des
 */
@Service
public class SystemLogServiceImpl extends ServiceImpl<SystemLogMapper, SystemLog> implements SystemLogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        IPage<SystemLog> page = this.page(
                new QueryUtils<SystemLog>().getPage(params),
                new QueryWrapper<SystemLog>().like(StringUtils.isNotBlank(key),"username", key)
        );

        return new PageUtils(page);
    }
}
