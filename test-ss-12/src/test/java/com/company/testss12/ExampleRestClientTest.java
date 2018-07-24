package com.company.testss12;

import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 接口测试+ SpringSecurity的用户登录模拟
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)// 事务自动回滚，默认是true。可以不写
public class ExampleRestClientTest {


    private MockMvc mockMvc; // 模拟MVC对象，通过MockMvcBuilders.webAppContextSetup(this.wac).build()初始化。

    @Autowired
    private WebApplicationContext wac; // 注入WebApplicationContext

    @Before // 在测试开始前初始化工作
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).apply(springSecurity()).build();
    }

    @Test
    @WithUserDetails(value = "admin", userDetailsServiceBeanName = "customUserDetailsService")
    public void testQ1() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("param1", "valueaa");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/secmenu/getUserMenuList")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(JSONObject.toJSONString(map)))
                .andExpect(status().is(200))// 模拟向testRest发送get请求
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

    }

    @Test
    public void testFormLoginSuccess() throws Exception {

        // 测试登录成功
        mockMvc
                .perform(formLogin("/login").user("admin").password("123456"))
                .andExpect(authenticated());
    }

    @Test
    public void testFormLoginFail() throws Exception {
        // 测试登录失败
        mockMvc
                .perform(formLogin("/login").user("admin").password("invalid"))
                .andExpect(unauthenticated());
    }

    @Test
    public void testLogoutFail() throws Exception {
        // 测试退出登录
        mockMvc.perform(logout("/logout")).andExpect(unauthenticated());
    }
}
