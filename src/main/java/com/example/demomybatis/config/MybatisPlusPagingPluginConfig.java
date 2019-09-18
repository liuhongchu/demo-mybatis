package com.example.demomybatis.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @liuhongchun
 * @Date 2019/9/16  11:42
 * @des MybatisPlus  分页插件
 */
@Configuration
public class MybatisPlusPagingPluginConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
