package com.company.testss12.service.impl;


import com.company.testss12.dao.SecMenuDao;
import com.company.testss12.entity.SecMenu;
import com.company.testss12.entity.SecUser;
import com.company.testss12.entity.vo.MenuVo;
import com.company.testss12.mapper.SecMenuMapper;
import com.company.testss12.service.SecMenuService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyp-company on 2018/6/26.
 */
@Service
public class SecMenuServiceImpl implements SecMenuService {


    @Resource
    private SecMenuMapper secMenuMapper;

    @Autowired
    private SecMenuDao secMenuDao;

    @Override
    public List<SecMenu> getStringPermissionList(SecUser secUser) {
        // TODO: 管理员直接给全部权限

        List<SecMenu> allMenu = new ArrayList<>();

        Integer[] roleIds = secUser.getRoleIds();
        List<SecMenu> menusByRoleIds = new ArrayList<>();
        List<SecMenu> menusByMenuIds = new ArrayList<>();
        if (roleIds != null && roleIds.length > 0) {
            // 按角色
            menusByRoleIds = secMenuDao.selectlistModuleByRoleIds(roleIds);

            allMenu.addAll(menusByRoleIds);
        }

        // 按个人身份取菜单  需去重
        Integer[] menuIds = secUser.getMenuIds();
        if (menuIds != null && menuIds.length > 0) {

            menusByMenuIds = secMenuDao.selectListByMenuIds(menuIds);

            for (SecMenu singleOne : menusByMenuIds) {

                // 去重
                boolean notRepeatedFlag = true;
                for (SecMenu singleOneInMenusByRoleIds : menusByRoleIds) {

                    if (singleOne.getId() == singleOneInMenusByRoleIds.getId()) {

                        notRepeatedFlag = false;
                        break;
                    }
                }

                if (notRepeatedFlag) {
                    allMenu.add(singleOne);
                }

            }
        }


        return allMenu;

    }


    public List<MenuVo> handleMenuList(List<SecMenu> secMenuList) {
        List<MenuVo> menuVos = new ArrayList<>();

        if (secMenuList != null && secMenuList.size() > 0) {
//            logger.debug("===================== 可访问资源的个数:{}================= ", secMenuList.size());
            for (SecMenu singleMenu : secMenuList) {

                // 如果是
                if (singleMenu.getState() == 1 && "#".equals(singleMenu.getUrl())
                        && "#".equals(singleMenu.getName())) {
                    continue;
                }

                MenuVo newModuleVo = new MenuVo();
                try {
                    BeanUtils.copyProperties(newModuleVo, singleMenu);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }

                if (singleMenu.getPid() == 0) {
                    menuVos.add(newModuleVo);

                } else {
                    for (MenuVo menuVo : menuVos) {
                        if (menuVo.getId() == singleMenu.getPid()) {
                            List<MenuVo> moduleVos2 = menuVo.getItems();
                            if (moduleVos2 == null) {
                                moduleVos2 = new ArrayList<>();
                                menuVo.setItems(moduleVos2);
                            }
                            moduleVos2.add(newModuleVo);
                            break;
                        }
                    }
                }
            }
        }
        return menuVos;
    }

    @Override
    public List<SecMenu> selectList() {
        return secMenuDao.selectList();
    }

    @Override
    public int addMenu(SecMenu secMenu) {
        return secMenuDao.addOne(secMenu);
    }

    @Override
    public int removeMenu(SecMenu secMenu) {
        return secMenuDao.removeOne(secMenu.getId());
    }

    @Override
    public int updateMenu(SecMenu secMenu) {
        return secMenuDao.updateOne(secMenu);
    }

    @Override
    public SecMenu getMenu(long id) {
        return secMenuDao.getOne(id);
    }

    @Override
    public List<SecMenu> getList(int state) {
        return secMenuDao.getList(state);
    }
}
