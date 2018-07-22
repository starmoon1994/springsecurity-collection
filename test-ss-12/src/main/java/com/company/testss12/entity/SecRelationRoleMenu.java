package com.company.testss12.entity;

/**
 * Created by hyp-company on 2018/6/19.
 */
public class SecRelationRoleMenu {

    private int id;
    private int roleId;
    private int menuId;
    private int acl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAcl() {
        return acl;
    }

    public void setAcl(int acl) {
        this.acl = acl;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
