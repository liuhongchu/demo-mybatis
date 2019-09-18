package com.example.demomybatis.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @liuhongchun
 * @Date 2019/9/16  11:30
 * @des
 */
public class ResultUtils extends HashMap<String, Object> {
    private static final long serialVersionUID = -5088393294574699463L;

    public ResultUtils() {
        put("code", 0);
        put("message", "success");
    }

    public static ResultUtils ResultError() {
        return ResultError(500, "未知异常，请联系管理员");
    }

    public static ResultUtils ResultError(String message) {
        return ResultError(500, message);
    }

    public static ResultUtils ResultError(int code, String message) {
        ResultUtils resultUtils = new ResultUtils();
        resultUtils.put("code", code);
        resultUtils.put("message", message);
        return resultUtils;
    }

    public static ResultUtils ResultOk(String message) {
        ResultUtils resultUtils = new ResultUtils();
        resultUtils.put("message", message);
        return resultUtils;
    }

    public static ResultUtils ResultOk(Map<String, Object> map) {
        ResultUtils resultUtils = new ResultUtils();
        resultUtils.putAll(map);
        return resultUtils;
    }

    public static ResultUtils ResultOk() {
        return new ResultUtils();
    }

    @Override
    public ResultUtils put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
