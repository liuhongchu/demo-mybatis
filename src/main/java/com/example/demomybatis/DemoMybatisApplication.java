package com.example.demomybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableTransactionManagement
@SpringBootApplication
@EnableSwagger2
@MapperScan("com.example.demomybatis.system.mapper")
public class DemoMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMybatisApplication.class, args);
    }

}
