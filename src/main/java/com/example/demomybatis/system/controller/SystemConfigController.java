package com.example.demomybatis.system.controller;

import com.example.demomybatis.system.entity.SystemConfig;
import com.example.demomybatis.system.service.SystemConfigService;
import com.example.demomybatis.utils.PageUtils;
import com.example.demomybatis.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @liuhongchun
 * @Date 2019/9/15  17:00
 */
@RestController
@RequestMapping("/system/config")
@Slf4j
@Api(tags = "系统设置接口")
public class SystemConfigController extends BaseController {
    @Autowired
    private SystemConfigService systemConfigService;

    /**
     * 所有配置列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:config:list")
    @ApiOperation("系统设置配置列表")
    public ResultUtils list(@RequestParam Map<String, Object> params){
        PageUtils page = systemConfigService.queryPage(params);
        return ResultUtils.ResultOk().put("page", page);
    }


    /**
     * 配置信息
     */
    @PostMapping("/info/{id}")
    @RequiresPermissions("sys:config:info")
    @ResponseBody
    public ResultUtils info(@PathVariable("id") Long id){
        SystemConfig config = systemConfigService.getById(id);
        return ResultUtils.ResultOk().put("config", config);
    }

    /**
     * 保存配置
     */
    @PostMapping("/save")
    @RequiresPermissions("sys:config:save")
    public ResultUtils save(@RequestBody SystemConfig config){
        // ValidatorUtils.validateEntity(config);
        systemConfigService.saveConfig(config);
        return ResultUtils.ResultOk();
    }

    /**
     * 修改配置
     */
    @PostMapping("/update")
    @RequiresPermissions("sys:config:update")
    public ResultUtils update(@RequestBody SystemConfig config){
        // ValidatorUtils.validateEntity(config);
        systemConfigService.update(config);
        return ResultUtils.ResultOk();
    }

    /**
     * 删除配置
     */
    @DeleteMapping("/delete")
    @RequiresPermissions("sys:config:delete")
    public ResultUtils delete(@RequestBody Long[] ids){
        systemConfigService.deleteBatch(ids);
        return ResultUtils.ResultOk();
    }

}
