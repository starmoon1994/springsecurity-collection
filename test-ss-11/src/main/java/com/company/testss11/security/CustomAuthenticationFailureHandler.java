package com.company.testss11.security;

import com.company.testss11.support.RetVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private String defaultFailureUrl;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomSecurityProperties customSecurityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {


        if (CustomSecurityProperties.loginResponseType.equals("JSON")) {

            RetVO retVO = new RetVO();
            retVO.setMsg("登录失败，原因：" + exception.getLocalizedMessage() + exception.getMessage());
            retVO.setData(exception.getCause());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(retVO));

        } else {
//            super.onAuthenticationFailure(request, response, exception);
            request.getRequestDispatcher(this.defaultFailureUrl).forward(request, response);
        }

    }

    public void setDefaultFailureUrl(String defaultFailureUrl) {
        Assert.isTrue(UrlUtils.isValidRedirectUrl(defaultFailureUrl), "'" + defaultFailureUrl + "' is not a valid redirect URL");
        this.defaultFailureUrl = defaultFailureUrl;
    }
}
