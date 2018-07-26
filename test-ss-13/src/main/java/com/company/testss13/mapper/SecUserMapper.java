package com.company.testss13.mapper;

import com.company.testss13.entity.SecUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SecUserMapper {
    public SecUser findByUsername(@Param("username") String username);

    public List<SecUser> selectList(@Param("state") int state);

    int addOne(SecUser secUser);

    int updateOne(SecUser secUser);

    SecUser selectOne(@Param("uid") long id);

    int removeUser(@Param("uid") int uid);
}
