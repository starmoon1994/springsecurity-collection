package com.company.testspringsecurity1.security;

import com.company.testspringsecurity1.security.support.SecurityConstants;
import com.company.testspringsecurity1.security.support.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;


@EnableWebSecurity
public class CustomSecurityConfig extends AbstractChannelSecurityConfig {

    @Autowired
    private UserDetailsService customUserDetailsService;
    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;


    /**
     * 核心配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //预处理HttpSecurity
        applyPasswordAuthenticationConfig(http);

        http
                .rememberMe()
                .userDetailsService(customUserDetailsService)
                .and()
                .sessionManagement()
                .invalidSessionStrategy(invalidSessionStrategy)
                .maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
                .maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
                .expiredSessionStrategy(sessionInformationExpiredStrategy)
                .and()
                .and()
                .authorizeRequests()
                .antMatchers(
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                        securityProperties.getBrowser().getSignUpUrl(),
                        securityProperties.getBrowser().getSession().getSessionInvalidUrl() + ".json",
                        securityProperties.getBrowser().getSession().getSessionInvalidUrl() + ".html",
                        "/user/regist")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();


    }

    @Autowired
    public void configAuthenticationManagerBuilder(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
//                .passwordEncoder();
//                .inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER")

    }


//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
//        tokenRepository.
//        return tokenRepository;
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
