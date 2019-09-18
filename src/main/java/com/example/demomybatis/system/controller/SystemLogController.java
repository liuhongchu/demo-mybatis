package com.example.demomybatis.system.controller;

import com.example.demomybatis.system.service.SystemLogService;
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
 * @Date 2019/9/15  17:01
 */
@RestController
@RequestMapping("/system/log")
@Slf4j
@Api(tags="系统日志接口")
public class SystemLogController extends BaseController{
    @Autowired
    private SystemLogService systemLogService;
    /**
     * 列表
     * @param params
     * @return
     */
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("sys:log:list")
    @ApiOperation("日志列表")
    public ResultUtils list(@RequestParam Map<String, Object> params){
        PageUtils page = systemLogService.queryPage(params);
        return ResultUtils.ResultOk().put("page", page);
    }
}
