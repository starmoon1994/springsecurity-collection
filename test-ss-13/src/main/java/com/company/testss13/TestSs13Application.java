package com.company.testss13;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
// mapper 接口类扫描包配置
@MapperScan("com.company.testss13.mapper")
@ImportResource(locations = {"classpath:spring-*.xml"})
public class TestSs13Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TestSs13Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(TestSs13Application.class);
    }


}
