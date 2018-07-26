package com.company.testss13.service;


import com.company.testss13.entity.SecMenu;
import com.company.testss13.entity.SecUser;
import com.company.testss13.entity.vo.MenuVo;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * Created by hyp-company on 2018/6/26.
 */
public interface SecMenuService {
    List<SecMenu> getStringPermissionList(SecUser secUser);

    /**
     * 组装结构化的菜单列表
     * @param secMenuList
     * @return
     */
    public List<MenuVo> handleMenuList(List<SecMenu> secMenuList);

    List<SecMenu> selectList();

    int addMenu(SecMenu secMenu);

    int removeMenu(SecMenu secMenu);

    int updateMenu(SecMenu secMenu);

    SecMenu getMenu(long id);

    List<SecMenu> getList(int state);

    List<MenuVo> getUserMenuList(UserDetails userDetails);
}
