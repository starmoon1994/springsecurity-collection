package com.company.testss12.mapper;

import com.company.testss12.entity.SecMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hyp-company on 2018/6/19.
 */
@Repository
public interface SecMenuMapper {

    public List<SecMenu> selectList();

    List<SecMenu> selectListByMenuIds(Integer[] menuIds);

    List<Integer> selectMenuIdListByRoleId(@Param("id") int id);

    List<SecMenu> selectlistModuleByRoleIds(Integer[] roleIds);

    int addOne(SecMenu secMenu);

    int removeOne(@Param("id") int id);

    int updateOne(SecMenu secMenu);

    SecMenu getOne();

    List<SecMenu> getList(@Param("state") int state);
}
