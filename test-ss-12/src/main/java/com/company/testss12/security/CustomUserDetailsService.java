package com.company.testss12.security;

import com.company.testss12.entity.SecRole;
import com.company.testss12.entity.SecUser;
import com.company.testss12.mapper.SecUserMapper;
import com.company.testss12.service.SecRoleService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


@Component
public class CustomUserDetailsService implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private SecUserMapper secUserMapper;

    @Autowired
    private SecRoleService secRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (StringUtils.isBlank(username)) {
            throw new UsernameNotFoundException("用户名不能为空");
        }
        // 账号未过期
        boolean accountNonExpired = true;
        // 密码未过期
        boolean credentialsNonExpired = true;
        // 账户未锁定
        boolean accountNonLocked = true;

        SecUser secUser = secUserMapper.findByUsername(username);
        // 验空
        if (secUser == null) {
            throw new UsernameNotFoundException("该用户不存在");
        }
        logger.info("表单登录用户名:" + username);
        logger.info("数据库密码是:" + secUser.getPassword());

        // 状态 验证state值 默认1是有效   2是锁定
        if (secUser.getState() == 2) {
            accountNonLocked = false;
        }

        // 过期
        Date nowDate = new Date();
        if (secUser.getExpireTime().before(nowDate)) {
            accountNonExpired = false;
        }

        // 抽取授权信息
        List<SecRole> secRoleList = secRoleService.getRoleList(secUser);
        String[] roleNames = new String[secRoleList.size()];
        for (int i = 0; i < secRoleList.size(); i++) {
            roleNames[i] = secRoleList.get(i).getCode();
        }

//        List<GrantedAuthority> grantedAuthorityList = AuthorityUtils.createAuthorityList("ROLE_ADMIN","ROLE_PM","ROLE_DEV");

        List<GrantedAuthority> grantedAuthorityList = AuthorityUtils.createAuthorityList(roleNames);

        // 构建 org.springframework.security.core.userdetails.User
        User user = new User(username, secUser.getPassword(), true, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorityList);

        return user;
    }

    public static void main(String[] args) {

        // 获取加密的密码
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        String encodePassword = bCryptPasswordEncoder.encode("123456");

        System.out.println("密码：" + encodePassword);
    }

}
