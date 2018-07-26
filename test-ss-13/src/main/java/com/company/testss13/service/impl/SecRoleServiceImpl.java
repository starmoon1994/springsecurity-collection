package com.company.testss13.service.impl;


import com.company.testss13.dao.SecRoleDao;
import com.company.testss13.entity.SecRelationRoleMenu;
import com.company.testss13.entity.SecRole;
import com.company.testss13.entity.SecUser;
import com.company.testss13.service.SecRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hyp-company on 2018/6/26.
 */
@Service
public class SecRoleServiceImpl implements SecRoleService {

    @Autowired
    private SecRoleDao secRoleDao;



    @Override
    public List<SecRole> getRoleList(SecUser secUser) {

        List<SecRole> secRoles = secRoleDao.selectListByRoleIds(secUser.getRoleIds());

        return secRoles;
    }

    @Override
    public int addRole(SecRole secRole) {
        return secRoleDao.addOne(secRole);
    }

    @Override
    public int removeRole(SecRole secRole) {
        return secRoleDao.removeRole(secRole.getId());
    }

    @Override
    public int updateRole(SecRole secRole) {
        return secRoleDao.updateRole(secRole);
    }

    @Override
    public SecRole getRole(long id) {
        return secRoleDao.selectOne(id);
    }

    @Override
    public List<SecRole> getList(int state) {
        return secRoleDao.getList(state);
    }

    @Override
    public List<SecRelationRoleMenu> getRoleMenuRelationList(int roleId, int menuId) {
        return secRoleDao.getRoleMenuRelationList(roleId, menuId);
    }

    @Override
    public int handleMenuAuth(int roleId, int menuId, int isValid) {

        List<SecRelationRoleMenu> roleMenuRelationList = secRoleDao.getRoleMenuRelationList(roleId, menuId);

        if (roleMenuRelationList.size() == 0){
            SecRelationRoleMenu newEntity = new SecRelationRoleMenu();
            newEntity.setRoleId(roleId);
            newEntity.setMenuId(menuId);
            newEntity.setAcl(isValid);
            return secRoleDao.addRoleMenuRel(newEntity);
        }else {

            SecRelationRoleMenu oldEntity = roleMenuRelationList.get(0);
            oldEntity.setRoleId(roleId);
            oldEntity.setMenuId(menuId);
            oldEntity.setAcl(isValid);
            return  secRoleDao.updateRoleMenuRel(oldEntity);
        }
    }
}
