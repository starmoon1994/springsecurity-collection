package com.company.testss12.security.handler;


import com.company.testss12.security.CustomSecurityProperties;
import com.company.testss12.support.RetVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 访问拒绝
 * <p>
 * 一般是对已经认证过  但授权不足的情况进行处理
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomSecurityProperties customSecurityProperties;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException, ServletException {
        if (CustomSecurityProperties.loginResponseType.equals("JSON")) {

            RetVO retVO = new RetVO();
            retVO.failure("权限不足，请联系管理员：" + exception.getLocalizedMessage() + exception.getMessage());
            retVO.setData(exception.getCause());
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(retVO));

        }
    }
}
