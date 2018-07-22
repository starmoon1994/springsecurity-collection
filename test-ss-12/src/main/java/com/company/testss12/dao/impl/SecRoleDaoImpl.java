package com.company.testss12.dao.impl;

import com.company.testss12.dao.SecRoleDao;
import com.company.testss12.entity.SecRelationRoleMenu;
import com.company.testss12.entity.SecRole;
import com.company.testss12.mapper.SecRelationRoleMenuMapper;
import com.company.testss12.mapper.SecRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hyp-company on 2018/6/29.
 */
@Repository
public class SecRoleDaoImpl implements SecRoleDao {

    @Autowired
    private SecRoleMapper secRoleMapper;


    @Autowired
    private SecRelationRoleMenuMapper secRelationRoleMenuMapper;


    @Override
    public List<SecRole> selectListByRoleIds(Integer[] roleIds) {
        return secRoleMapper.selectListByRoleIds(roleIds);
    }

    @Override
    public int addOne(SecRole secRole) {
        return secRoleMapper.addOne(secRole);
    }

    @Override
    public int updateRole(SecRole secRole) {
        return secRoleMapper.updateOne(secRole);
    }

    @Override
    public int removeRole(int id) {
        return secRoleMapper.removeRole(id);
    }

    @Override
    public SecRole selectOne(long id) {
        return secRoleMapper.selectOne(id);
    }

    @Override
    public List<SecRole> getList(int state) {
        return secRoleMapper.getList(state);
    }

    @Override
    public List<SecRelationRoleMenu> getRoleMenuRelationList(int roleId, int menuId) {
        return secRoleMapper.getRoleMenuRelationList(roleId, menuId);
    }


    @Override
    public int addRoleMenuRel(SecRelationRoleMenu newEntity) {
        return secRoleMapper.addRoleMenuRel(newEntity);
    }

    @Override
    public int updateRoleMenuRel(SecRelationRoleMenu oldEntity) {
        return secRoleMapper.updateRoleMenuRel(oldEntity);
    }
}
