package com.company.testss12.dao;

import com.company.testss12.entity.SecUser;

import java.util.List;

/**
 * Created by hyp-company on 2018/6/29.
 */
public interface SecUserDao {
    int updateOne(SecUser secUser);

    int addOne(SecUser secUser);

    SecUser selectOne(long id);

    List<SecUser> selectList(int state);

    int removeUser(Integer uid);

}
