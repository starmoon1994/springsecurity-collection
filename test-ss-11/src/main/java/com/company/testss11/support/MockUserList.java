package com.company.testss11.support;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟用户列表
 *
 */
public class MockUserList {

    public static SecUser getMockUser(String username) {

        for (SecUser secUser : userList) {
            if (secUser.getUsername().equals(username)) {
                return secUser;
            }
        }

        return null;
    }


    static List<SecUser> userList = new ArrayList<>();

    static {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodePassword = bCryptPasswordEncoder.encode("123456");

        SecUser secUser = new SecUser();
        secUser.setId(1);
        secUser.setUsername("admin");
        secUser.setPassword(encodePassword);
        secUser.setRoles(new String[]{"ROLE_ADMIN", "ROLE_USER", "ROLE_BD"});
        secUser.setPermissions("ADMIN:get");
        userList.add(secUser);


        SecUser secUser2 = new SecUser();
        secUser2.setId(2);
        secUser2.setUsername("bd");
        secUser2.setPassword(encodePassword);
        secUser2.setRoles(new String[]{"ROLE_USER", "ROLE_BD"});
        secUser2.setPermissions("BD:get");
        userList.add(secUser2);

    }


    public static void main(String[] args) {

        // 获取加密的密码
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        String encodePassword = bCryptPasswordEncoder.encode("123456");

        System.out.println("密码：" + encodePassword);
    }

}


