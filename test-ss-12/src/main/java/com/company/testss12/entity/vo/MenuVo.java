package com.company.testss12.entity.vo;

import com.company.testss12.entity.SecMenu;

import java.util.List;

/**
 * 菜单列表  视图包装类
 */
public class MenuVo extends SecMenu {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private List<MenuVo> items;

    public List<MenuVo> getItems() {
        return items;
    }

    public void setItems(List<MenuVo> items) {
        this.items = items;
    }

}
