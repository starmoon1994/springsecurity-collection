package com.company.testss12.security;

import com.company.testss12.security.handler.CustomAccessDeniedHandler;
import com.company.testss12.security.handler.CustomAuthenticationFailureHandler;
import com.company.testss12.security.handler.CustomAuthenticationSuccessHandler;
import com.company.testss12.security.handler.CustomLogoutSuccessHandler;
import com.company.testss12.security.session.CustomExpiredSessionStrategy;
import com.company.testss12.security.session.CustomInvalidSessionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * spring security的主配置类
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class CustomApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

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
        configSession(http);

        http
                .csrf().disable()
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);
    }


    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;

    // 配置session相关
    private void configSession(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .invalidSessionStrategy(invalidSessionStrategy)
                .invalidSessionUrl(CustomSecurityProperties.invalidSessionUrl)
                .maximumSessions(1)
                .expiredSessionStrategy(sessionInformationExpiredStrategy) //session失效策略
                .expiredUrl(CustomSecurityProperties.expiredSessionUrl);
    }

    // 配置请求授权相关
    private void configAuthorizeRequests(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(CustomSecurityProperties.exclusivePaths)
                .permitAll()
                .antMatchers("/admin/**", "/**/delete").hasAnyRole("ADMIN")
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

    /**
     * 配置sec的session失效策略
     * 配置给sessionManagement
     */
    @Bean
    @ConditionalOnMissingBean(InvalidSessionStrategy.class)
    public InvalidSessionStrategy invalidSessionStrategy() {
        return new CustomInvalidSessionStrategy(CustomSecurityProperties.invalidSessionUrl);
    }

    /**
     * 配置sec的session过期策略
     * 配置给sessionManagement
     */
    @Bean
    @ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
    public SessionInformationExpiredStrategy sessionInformationExpiredStrategy() {
        return new CustomExpiredSessionStrategy(CustomSecurityProperties.invalidSessionUrl);
    }


    @Bean
    public CookieHttpSessionStrategy cookieHttpSessionStrategy() {
        CookieHttpSessionStrategy strategy = new CookieHttpSessionStrategy();
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        cookieSerializer.setCookieName("gs");
        cookieSerializer.setCookiePath("/");
        cookieSerializer.setCookieMaxAge(60 * 60 * 24 * 30);
        strategy.setCookieSerializer(cookieSerializer);
        return strategy;
    }
}
