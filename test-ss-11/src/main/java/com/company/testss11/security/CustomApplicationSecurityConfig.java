package com.company.testss11.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * spring security的主配置类
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class CustomApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomSecurityProperties customSecurityProperties;

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        configAuthorizeRequests(http);
        configFormLogin(http);
        configLogout(http);

        http
                .csrf().disable()
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler)
        .and().sessionManagement()
                .invalidSessionUrl("/sessionInvalidSessionUrl")
                .maximumSessions(2)
                .expiredUrl("/sessionExpiredUrl");
    }


    // 配置请求授权相关
    private void configAuthorizeRequests(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(CustomSecurityProperties.exclusivePaths)
                .permitAll()
                .antMatchers("/admin/**","/**/delete").hasAnyRole("ADMIN")
                .anyRequest()
                .authenticated();
    }


    // 配置表单登录操作
    private void configFormLogin(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(CustomSecurityProperties.formLogin_loginPage)
                .loginProcessingUrl(CustomSecurityProperties.formLogin_loginProcessingUrl) // 在自定义SuccessHandler已经处理 直接返回json 这里的跳转没啥卵用了
                .successForwardUrl(CustomSecurityProperties.formLogin_successForwardUrl)
                .usernameParameter(CustomSecurityProperties.formLogin_usernameParameter)
                .passwordParameter(CustomSecurityProperties.formLogin_passwordParameter)
                .permitAll(true)
                .failureHandler(customAuthenticationFailureHandler)
                .successHandler(customAuthenticationSuccessHandler);
    }


    // 配置退出登录操作
    private void configLogout(HttpSecurity http) throws Exception {

        http.logout()
                .logoutUrl(CustomSecurityProperties.logout_logoutUrl)
                .permitAll()
                .clearAuthentication(true)
                .deleteCookies(CustomSecurityProperties.cookieNames)
                .logoutSuccessHandler(customLogoutSuccessHandler);
    }


    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }



}
