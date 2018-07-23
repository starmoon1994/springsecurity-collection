package com.company.testss12.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.company.testss12.security.CustomSecurityProperties;
import com.company.testss12.support.RetVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMaper;

    @Autowired
    private CustomSecurityProperties customSecurityProperties;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        // SimpleUrlAuthenticationSuccessHandler是直接重定向
        // 改造成返回json

        SecurityContext context = SecurityContextHolder.getContext();


        if (CustomSecurityProperties.loginResponseType.equals("JSON")) {


            JSONObject retJSONObject = new JSONObject();
            retJSONObject.put("name", authentication.getName());
            retJSONObject.put("authorities", authentication.getAuthorities());

            RetVO retVO = new RetVO();
            retVO.setMsg("登录成功");
            retVO.setData(retJSONObject);
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(objectMaper.writeValueAsString(retVO));
        } else {
            // 非json 就直接跳转
            super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);

        }


    }
}
