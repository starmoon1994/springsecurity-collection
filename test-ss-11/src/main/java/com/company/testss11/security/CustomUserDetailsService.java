package com.company.testss11.security;

import com.company.testss11.support.MockUserList;
import com.company.testss11.support.SecUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CustomUserDetailsService implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SecUser mockUser = MockUserList.getMockUser(username);

        if (mockUser == null) {
            throw new UsernameNotFoundException("该用户不存在");
        }

        logger.info("表单登录用户名:" + username);
        logger.info("数据库密码是:" + mockUser.getPassword());

        List<GrantedAuthority> grantedAuthorityList = AuthorityUtils.createAuthorityList(mockUser.getRoles());

        // 构建 org.springframework.security.core.userdetails.User
        User user = new User(username, mockUser.getPassword(), true, true,
                true, true, grantedAuthorityList);

        return user;
    }
}
