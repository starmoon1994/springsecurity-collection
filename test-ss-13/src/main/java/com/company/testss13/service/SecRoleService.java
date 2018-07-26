package com.company.testss13.service;

import com.company.testss13.entity.SecRelationRoleMenu;
import com.company.testss13.entity.SecRole;
import com.company.testss13.entity.SecUser;

import java.util.List;

/**
 * Created by hyp-company on 2018/6/26.
 */
public interface SecRoleService {
    List<SecRole> getRoleList(SecUser secUser);

    int addRole(SecRole secRole);

    int removeRole(SecRole secRole);

    int updateRole(SecRole secRole);

    SecRole getRole(long id);

    List<SecRole> getList(int state);

    List<SecRelationRoleMenu> getRoleMenuRelationList(int roleId, int menuId);

    int handleMenuAuth(int roleId, int menuId, int isValid);
}
