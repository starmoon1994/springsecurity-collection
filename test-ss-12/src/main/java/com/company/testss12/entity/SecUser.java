package com.company.testss12.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hyp-company on 2018/6/16.
 * <p>
 * sec_user 用户表
 * sec_role 角色表
 * sec_menu 前端菜单信息表
 * sec_relation_user_role 用户-角色关联表（可去掉 在用户信息中直接存角色id）
 * sec_relation_role_menu 角色-菜单关联表
 */
public class SecUser implements Serializable {

    private static final long serialVersionUID = 1456217245177063596L;

    private Integer uid;
    // 昵称
    private String name;
    // 登录名
    private String username;
    // 密码
    private String password;
    // 密码盐
    private String salt;
    // 状态 0 全部  1 正常（默认） 2 挂起 4 禁止登录
    private Integer state;
    // 角色信息
    private Integer[] roleIds;
    // 授权菜单信息（单独授权）
    private Integer[] menuIds;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date expireTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getUsername() {
        return username;
    }

    public Integer[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Integer[] roleIds) {
        this.roleIds = roleIds;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Integer[] getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(Integer[] menuIds) {
        this.menuIds = menuIds;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * 密码盐.
     *
     * @return
     */
    public String getCredentialsSalt() {
        return "";
//        return this.username + this.salt;
    }


}
