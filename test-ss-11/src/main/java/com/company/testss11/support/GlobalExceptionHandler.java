package com.company.testss11.support;


import com.company.testss11.support.exception.BusinessException;
import com.company.testss11.support.exception.UnloginException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 统一异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = UnloginException.class)
    public void unLoginHandler(HttpServletRequest req, HttpServletResponse response, Exception e) throws Exception {
        logger.error("登录信息失效异常", e);
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public void UsernameNotFoundExceptionHandler(HttpServletRequest req, HttpServletResponse response, Exception e) throws Exception {
        logger.error("UsernameNotFoundException异常", e);

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public void AccessDeniedExceptionHandler(HttpServletRequest req, HttpServletResponse response, Exception e) throws Exception {
        logger.error("AccessDeniedException异常 权限不足", e);
        logger.error("AccessDeniedException异常 req.getRemoteHost()",req.getRemoteHost());

        response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
    }


    @ExceptionHandler(value = BusinessException.class)
    public void businessHandler(HttpServletRequest req, HttpServletResponse response, Exception e) throws Exception {
        logger.error("业务异常", e);
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public void jsonErrorHandler(HttpServletRequest req, HttpServletResponse response, Exception e) throws Exception {
        logger.error("其他异常", e);
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
    }

}
