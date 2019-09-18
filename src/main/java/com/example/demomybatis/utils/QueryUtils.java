package com.example.demomybatis.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demomybatis.filter.SQLFilter;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * @liuhongchun
 * @Date 2019/9/16  13:30
 * @des
 */
public class QueryUtils<T> {
    public IPage<T> getPage(Map<String, Object> params) {
        return this.getPage(params, null, false);
    }

    public IPage<T> getPage(Map<String, Object> params, String defaultOrderField, boolean isAsc) {
        //分页参数
        long curPage = 1;
        long limit = 10;

        if(params.get(ConstantUtils.PAGE) != null){
            curPage = Long.parseLong((String)params.get(ConstantUtils.PAGE));
        }
        if(params.get(ConstantUtils.LIMIT) != null){
            limit = Long.parseLong((String)params.get(ConstantUtils.LIMIT));
        }

        //分页对象
        Page<T> page = new Page<>(curPage, limit);

        //分页参数
        params.put(ConstantUtils.PAGE, page);

        //排序字段
        //防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
        String orderField = SQLFilter.sqlInject((String)params.get(ConstantUtils.ORDER_FIELD));
        String order = (String)params.get(ConstantUtils.ORDER);

        //前端字段排序
        if(StringUtils.isNotEmpty(orderField) && StringUtils.isNotEmpty(order)){
            if(ConstantUtils.ASC.equalsIgnoreCase(order)) {
                return  page.addOrder(OrderItem.asc(orderField));
            }else {
                return page.addOrder(OrderItem.desc(orderField));
            }
        }

        //没有排序字段，则不排序
        if(StringUtils.isBlank(defaultOrderField)){
            return page;
        }

        //默认排序
        if(isAsc) {
            page.addOrder(OrderItem.asc(defaultOrderField));
        }else {
            page.addOrder(OrderItem.desc(defaultOrderField));
        }

        return page;
    }
}
