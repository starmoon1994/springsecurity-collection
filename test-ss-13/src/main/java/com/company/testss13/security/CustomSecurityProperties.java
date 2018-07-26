package com.company.testss13.security;

import org.springframework.stereotype.Component;

@Component("customSecurityProperties")
//@ConfigurationProperties(prefix = "custom.security")
public class CustomSecurityProperties {

    // 登录交互类型  JSON:全程使用json进行数据交互   HTML_REDIRECT 页面跳转
    public static final String loginResponseType = "JSON";
    public static final String formLogin_loginPage = "/needlogin";
    // 处理登录认证的url   图片验证码用form   手机验证码用mobile
    public static final String formLogin_loginProcessingUrl = "/authentication/form";
    public static final String formLogin_successForwardUrl = "/login";
    // 登录时表单参数 用户名
    public static final String formLogin_usernameParameter = "username";
    // 登录时表单参数 密码
    public static final String formLogin_passwordParameter = "password";
    // 退出登录的参数
    public static final String logout_logoutUrl = "/logout";
    public static final String invalidSessionUrl = "/sessionInvalidSessionUrl";
    public static final String expiredSessionUrl = "/sessionExpiredUrl";
    public static final String[] exclusivePaths = {
            "/css/**", "/js/**", "/fonts/**", "/favicon.ico",
            "/index", "/needlogin", "/loginPage", "/401", "/404",
            "/nopermission", "/authentication/form", "/authentication/mobile",
            "/code/**",
    };
    // cookie名字列表 退出时需要清除
    public static final String[] cookieNames = {"JSESSIONID", "gs", "g_username", "g_id"};

    public static final String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/authentication/form";
    public static final String DEFAULT_LOGIN_PROCESSING_URL_MOBILE = "/authentication/mobile";

    /**
     * 验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";
    /**
     * 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";
    /**
     * 发送短信验证码 或 验证短信验证码时，前端传递手机号的参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";
    /**
     * session失效默认的跳转地址
     */
    public static final String DEFAULT_SESSION_INVALID_URL = "/session/invalid";

    /**
     * 默认的处理验证码的url前缀
     */
    public static final String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";
    /**
     * 当请求需要身份认证时，默认跳转的url
     *
     * @see
     */
    public static final String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";

    /**
     * 验证码过期时间 单位秒
     */
    public static final int  VALIDATE_CODE_EXPIREIN = 60;
}
