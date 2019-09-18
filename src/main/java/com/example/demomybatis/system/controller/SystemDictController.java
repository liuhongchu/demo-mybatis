package com.example.demomybatis.system.controller;

import com.example.demomybatis.system.entity.SystemDict;
import com.example.demomybatis.system.service.SystemDictService;
import com.example.demomybatis.utils.PageUtils;
import com.example.demomybatis.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * @liuhongchun
 * @Date 2019/9/15  17:01
 */
@RestController
@RequestMapping("/system/dict")
@Slf4j
@Api(tags = "系统字典接口")
public class SystemDictController extends BaseController {
    @Autowired
    SystemDictService systemDictService;
    /**
     * 列表
     * @param params
     * @return
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:dict:list")
    @ApiOperation("字典列表")
    public ResultUtils list(@RequestParam Map<String, Object> params) {
        PageUtils page = systemDictService.queryPage(params);
        return ResultUtils.ResultOk().put("page", page);
    }
    /**
     * 信息
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:dict:info")
    @ApiOperation("字典信息")
    public ResultUtils info(@PathVariable("id") Long id) {
        SystemDict dict = systemDictService.getById(id);
        return ResultUtils.ResultOk().put("dict", dict);
    }
    /**
     * 保存
     * @param dict
     * @return
     */
    @PostMapping("/save")
    @RequiresPermissions("sys:dict:save")
    @ApiOperation("字典保存")
    public ResultUtils save(@RequestBody SystemDict dict) {
        //校验类型
        // ValidatorUtils.validateEntity(dict);
        systemDictService.save(dict);
        return ResultUtils.ResultOk();
    }
    /**
     * 修改
     * @param dict
     * @return
     */
    @PostMapping("/update")
    @RequiresPermissions("sys:dict:update")
    @ApiOperation("字典修改")
    public ResultUtils update(@RequestBody SystemDict dict) {
        //校验类型
        // ValidatorUtils.validateEntity(dict);
        systemDictService.updateById(dict);
        return ResultUtils.ResultOk();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @RequiresPermissions("sys:dict:delete")
    @ApiOperation("字典删除")
    public ResultUtils delete(@RequestBody Long[] ids) {
        systemDictService.removeByIds(Arrays.asList(ids));
        return ResultUtils.ResultOk();
    }

}
