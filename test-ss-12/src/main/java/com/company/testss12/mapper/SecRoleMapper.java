package com.company.testss12.mapper;


import com.company.testss12.entity.SecRelationRoleMenu;
import com.company.testss12.entity.SecRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hyp-company on 2018/6/19.
 */
public interface SecRoleMapper {
    public List<SecRole> selectList();

    List<SecRole> selectListByRoleIds(Integer[] roleIds);

    int addOne(SecRole secRole);

    int updateOne(SecRole secRole);

    int removeRole(@Param("id") int id);

    SecRole selectOne(@Param("id") long id);

    List<SecRole> getList(@Param("state") int state);

    List<SecRelationRoleMenu> getRoleMenuRelationList(@Param("roleId") int roleId, @Param("menuId") int menuId);

    int addRoleMenuRel(SecRelationRoleMenu newEntity);

    int updateRoleMenuRel(SecRelationRoleMenu oldEntity);
}
