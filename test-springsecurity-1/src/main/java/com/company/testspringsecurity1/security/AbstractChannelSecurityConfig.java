package com.company.testspringsecurity1.security;

import com.company.testspringsecurity1.security.support.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    protected AuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler customAuthenticationFailureHandler;


    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)  // 登录页
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler);
    }
}
