//package com.company.testss13.security.validate.code.simple;
//
//
//import com.company.testss13.security.validate.code.ValidateCode;
//import com.company.testss13.security.validate.code.ValidateCodeException;
//import com.company.testss13.security.validate.code.ValidateCodeType;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.social.connect.web.HttpSessionSessionStrategy;
//import org.springframework.social.connect.web.SessionStrategy;
//import org.springframework.stereotype.Component;
//import org.springframework.util.AntPathMatcher;
//import org.springframework.web.bind.ServletRequestBindingException;
//import org.springframework.web.bind.ServletRequestUtils;
//import org.springframework.web.context.request.ServletWebRequest;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//
///**
// * 简单版本的图片验证码filter
// * @author starmoon1994
// */
//@Component("validateCodeFilter")
//public class SimpleValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {
//
//    /**
//     * 验证码校验失败处理器
//     */
//    @Autowired
//    private AuthenticationFailureHandler authenticationFailureHandler;
//
//    /**
//     * 存放所有需要校验验证码的url
//     */
//    private Map<String, ValidateCodeType> urlMap = new HashMap<>();
//    /**
//     * 验证请求url与配置的url是否匹配的工具类
//     */
//    private AntPathMatcher pathMatcher = new AntPathMatcher();
//
//    /**
//     * 初始化要拦截的url配置信息
//     */
//    @Override
//    public void afterPropertiesSet() throws ServletException {
//        super.afterPropertiesSet();
//
//        urlMap.put("/authentication/form", ValidateCodeType.IMAGE);
//
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws ServletException, IOException {
//
//
//        ServletWebRequest servletWebRequest = new ServletWebRequest(request, response);
//        SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
//
//        ValidateCodeType type = getValidateCodeType(request);
//        if (type != null) {
//            logger.info("校验请求(" + request.getRequestURI() + ")中的验证码,验证码类型" + type);
//            try {
//
//                ValidateCodeType processorType = getValidateCodeType(request);
//                String sessionKey = "SESSION_KEY_FOR_CODE_IMAGE";
//
//                // 从session中取验证码
//                Object sessionStrategyAttribute = sessionStrategy.getAttribute(servletWebRequest, sessionKey);
//                if (sessionStrategyAttribute == null) {
//                    throw new ValidateCodeException(processorType + "验证码不存在");
//                }
//                ValidateCode codeInSession = (ValidateCode) sessionStrategyAttribute;
//
//                String codeInRequest;
//                try {
//                    codeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(),
//                            processorType.getParamNameOnValidate());
//                } catch (ServletRequestBindingException e) {
//                    throw new ValidateCodeException("获取验证码的值失败");
//                }
//
//                if (StringUtils.isBlank(codeInRequest)) {
//                    throw new ValidateCodeException(processorType + "验证码的值不能为空");
//                }
//
//                if (codeInSession == null) {
//                    throw new ValidateCodeException(processorType + "验证码不存在");
//                }
//
//                if (codeInSession.isExpried()) {
//                    sessionStrategy.removeAttribute(servletWebRequest, sessionKey);
//                    throw new ValidateCodeException(processorType + "验证码已过期");
//                }
//
//                if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
//                    throw new ValidateCodeException(processorType + "验证码不匹配");
//                }
//
//                sessionStrategy.removeAttribute(servletWebRequest, sessionKey);
//                logger.info("验证码校验通过");
//            } catch (ValidateCodeException exception) {
//                authenticationFailureHandler.onAuthenticationFailure(request, response, exception);
//                return;
//            }
//        }
//
//        chain.doFilter(request, response);
//
//    }
//
//    /**
//     * 获取验证码的类型，如果当前请求不需要校验，则返回null
//     *
//     * @param request
//     * @return
//     */
//    private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
//        ValidateCodeType result = null;
//        if (!StringUtils.equalsIgnoreCase(request.getMethod(), "get")) {
//            Set<String> urls = urlMap.keySet();
//            for (String url : urls) {
//                if (pathMatcher.match(url, request.getRequestURI())) {
//                    result = urlMap.get(url);
//                }
//            }
//        }
//        return result;
//    }
//
//}
