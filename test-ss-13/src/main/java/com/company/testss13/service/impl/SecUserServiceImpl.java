package com.company.testss13.service.impl;

import com.company.testss13.dao.SecUserDao;
import com.company.testss13.entity.SecUser;
import com.company.testss13.service.SecUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by hyp-company on 2018/6/29.
 */
@Service
public class SecUserServiceImpl implements SecUserService {

    @Autowired
    private SecUserDao secUserDao;

    @Override
    public int addUser(SecUser secUser) {
        Date nowDate = new Date();
        secUser.setCreateTime(nowDate);
        secUser.setModifyTime(nowDate);

        return secUserDao.addOne(secUser);
    }

    @Override
    public int removeUser(SecUser secUser) {

        return secUserDao.removeUser(secUser.getUid());
    }

    @Override
    public int updateUser(SecUser secUser) {

        Date nowDate = new Date();
        secUser.setModifyTime(nowDate);

        return secUserDao.updateOne(secUser);
    }

    @Override
    public SecUser getUser(long id) {
       return secUserDao.selectOne(id);
    }

    @Override
    public List<SecUser> getList(int state) {

        return secUserDao.selectList(state);
    }

    @Override
    public SecUser findByUsername(String username) {
        return secUserDao.findByUsername(username);
    }
}
