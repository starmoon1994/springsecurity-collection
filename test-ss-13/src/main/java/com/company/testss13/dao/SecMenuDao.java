package com.company.testss13.dao;

import com.company.testss13.entity.SecMenu;

import java.util.List;

/**
 * Created by hyp-company on 2018/6/29.
 */
public interface SecMenuDao {
    List<SecMenu> selectlistModuleByRoleIds(Integer[] roleIds);

    List<SecMenu> selectListByMenuIds(Integer[] menuIds);

    List<SecMenu> selectList();

    int addOne(SecMenu secMenu);

    int removeOne(int id);

    int updateOne(SecMenu secMenu);

    SecMenu getOne(long id);

    List<SecMenu> getList(int state);
}
