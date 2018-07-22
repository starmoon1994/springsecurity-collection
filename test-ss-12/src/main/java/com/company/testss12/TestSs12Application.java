package com.company.testss12;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
// mapper 接口类扫描包配置
@MapperScan("com.company.testss12.mapper")
@ImportResource(locations = {"classpath:spring-*.xml"})
public class TestSs12Application {

    public static void main(String[] args) {
        SpringApplication.run(TestSs12Application.class, args);
    }


}
