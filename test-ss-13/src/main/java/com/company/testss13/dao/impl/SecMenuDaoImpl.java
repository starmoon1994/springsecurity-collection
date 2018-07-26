package com.company.testss13.dao.impl;

import com.company.testss13.dao.SecMenuDao;
import com.company.testss13.entity.SecMenu;
import com.company.testss13.mapper.SecMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hyp-company on 2018/6/29.
 */
@Repository
public class SecMenuDaoImpl implements SecMenuDao {

    @Autowired
    private SecMenuMapper secMenuMapper;

    @Override
    public List<SecMenu> selectlistModuleByRoleIds(Integer[] roleIds) {
        return secMenuMapper.selectlistModuleByRoleIds(roleIds);
    }

    @Override
    public List<SecMenu> selectListByMenuIds(Integer[] menuIds) {
        return secMenuMapper.selectListByMenuIds(menuIds);
    }

    @Override
    public List<SecMenu> selectList() {
        return secMenuMapper.selectList();
    }

    @Override
    public int addOne(SecMenu secMenu) {
        return secMenuMapper.addOne(secMenu);
    }

    @Override
    public int removeOne(int id) {
        return secMenuMapper.removeOne(id);
    }

    @Override
    public int updateOne(SecMenu secMenu) {
        return secMenuMapper.updateOne(secMenu);
    }

    @Override
    public SecMenu getOne(long id) {
        return secMenuMapper.getOne();
    }

    @Override
    public List<SecMenu> getList(int state) {
        return secMenuMapper.getList(state);
    }
}
