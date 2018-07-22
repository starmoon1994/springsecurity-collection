package com.company.testss11.support;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomWebMvcConfigurer  implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 指定注册一些url到视图注册器
        registry.addViewController("/login").setViewName("loginPage");
        registry.addViewController("/index").setViewName("indexPage");
        registry.addViewController("/401").setViewName("401Page");
        registry.addViewController("/404").setViewName("404Page");
        registry.addViewController("/nopermission").setViewName("nopermissionPage");
    }
}
