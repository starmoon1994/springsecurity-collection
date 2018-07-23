package com.company.testss12.support;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.company.testss12.support.page.PageFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

//import org.springframework.boot.autoconfigure.http.HttpMessageConverters;

@Configuration
public class CustomWebMvcConfigurer extends WebMvcConfigurerAdapter {

    // springboot1.5.6用WebMvcConfigurerAdapter  springboot2.0.3用WebMvcConfigurer


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 指定注册一些url到视图注册器
        registry.addViewController("/login").setViewName("loginPage");
        registry.addViewController("/index").setViewName("indexPage");
        registry.addViewController("/401").setViewName("401Page");
        registry.addViewController("/404").setViewName("404Page");
        registry.addViewController("/nopermission").setViewName("nopermissionPage");
    }


    private final Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);


    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect);

        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(supportedMediaTypes);

        fastConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter = fastConverter;
        return new HttpMessageConverters(converter);
    }


    @Bean("pageFilter")
    public PageFilter PageFilter() {
        return new PageFilter();
    }

    @Bean
    public FilterRegistrationBean pageFilterRegistrationBean(PageFilter pageFilter) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setName("pageFilter");
        filterRegistrationBean.setFilter(pageFilter);
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.addUrlPatterns("*.do");
        filterRegistrationBean.addUrlPatterns("*.htm");
        return filterRegistrationBean;
    }
}
