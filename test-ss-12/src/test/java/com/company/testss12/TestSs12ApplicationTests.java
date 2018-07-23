package com.company.testss12;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSs12ApplicationTests {

    @Test
    @WithUserDetails(value = "admin", userDetailsServiceBeanName = "customUserDetailsService")
    public void contextLoads() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println(authentication);


    }

}
