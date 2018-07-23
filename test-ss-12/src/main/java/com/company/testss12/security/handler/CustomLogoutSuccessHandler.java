package com.company.testss12.security.handler;

import com.company.testss12.security.CustomSecurityProperties;
import com.company.testss12.support.RetVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
    @Autowired
    private ObjectMapper objectMaper;

    @Autowired
    private CustomSecurityProperties customSecurityProperties;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        if (CustomSecurityProperties.loginResponseType.equals("JSON")) {
            RetVO retVO = new RetVO();
            retVO.setMsg("退出登录成功");
            retVO.setData(authentication);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMaper.writeValueAsString(retVO));
        } else {

            super.handle(request, response, authentication);
        }
    }
}
