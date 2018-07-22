package com.company.testss12.security;

import com.company.testss12.support.RetVO;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 对Spring Security的一些url进行支持
 */
@Controller
public class SecuritySupportController {


    /**
     * 未认证的请求 将跳转到本接口
     * 返回一个401状态码
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/needlogin")
    @ResponseBody
    public RetVO index(HttpServletRequest request, HttpServletResponse response) {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        RetVO retVO = new RetVO();
        retVO.setMsg("needLogin");

        return retVO;
    }

    @RequestMapping("/sessionInvalidSessionUrl")
    @ResponseBody
    public RetVO sessionInvalidSessionUrl(HttpServletRequest request, HttpServletResponse response) {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        RetVO retVO = new RetVO();
        retVO.setMsg("sessionInvalidSessionUrl，session无效，请重新登录");

        return retVO;
    }

    @RequestMapping("/sessionExpiredUrl")
    @ResponseBody
    public RetVO sessionExpiredUrl(HttpServletRequest request, HttpServletResponse response) {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        RetVO retVO = new RetVO();
        retVO.setMsg("sessionExpiredUrl，session过期，请重新登录");

        return retVO;
    }


    /**
     * 测试认证是否能生效
     *
     * @return
     */
    @RequestMapping("/sstest")
    @ResponseBody
    @Secured("ROLE_ADMIN")
    public RetVO test() {

        RetVO retVO = new RetVO();
        retVO.setMsg("已认证  未认证时访问本接口会被401");

        return retVO;
    }


    /**
     * 测试认证是否能生效
     *
     * @return
     */
    @RequestMapping("/sstest2")
    @ResponseBody
    @Secured("ROLE_BD")
    public RetVO test2() {

        RetVO retVO = new RetVO();
        retVO.setMsg("已认证  成功访问  本接口需要权限ROLE_BD");

        return retVO;
    }
}
