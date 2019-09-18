package com.example.demomybatis.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.service.Parameter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {
        /***添加请求参数，我们这里把token作为请求头部参数传入后端*/
         ParameterBuilder parameterBuilder = new ParameterBuilder();
         parameterBuilder.name("token").description("令牌")
         .modelRef(new ModelRef("string")).parameterType("header").required(false).build();
         List<Parameter> parameterList = new ArrayList<Parameter>();
         parameterList.add(parameterBuilder.build());

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //加了ApiOperation注解的类，生成接口文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //接口对应包下的类，生成API文档
                .apis(RequestHandlerSelectors.basePackage("com.example.demomybatis.system.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameterList);
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("DEMO")
                .description("DEMO后台管理系统API文档")
                .termsOfServiceUrl("https://swagger.io/")
                .version("1.18.10")
                .build();
    }
}
