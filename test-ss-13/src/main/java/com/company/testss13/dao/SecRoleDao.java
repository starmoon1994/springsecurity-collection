package com.company.testss13.dao;

import com.company.testss13.entity.SecRelationRoleMenu;
import com.company.testss13.entity.SecRole;

import java.util.List;

/**
 * Created by hyp-company on 2018/6/29.
 */
public interface SecRoleDao {

    List<SecRole> selectListByRoleIds(Integer[] roleIds);

    int addOne(SecRole secRole);

    int updateRole(SecRole secRole);

    int removeRole(int id);

    SecRole selectOne(long id);

    List<SecRole> getList(int state);

    List<SecRelationRoleMenu> getRoleMenuRelationList(int roleId, int menuId);

    int addRoleMenuRel(SecRelationRoleMenu newEntity);

    int updateRoleMenuRel(SecRelationRoleMenu oldEntity);
}
