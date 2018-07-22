package com.company.testss12.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 菜单信息
 * 储存前端路由
 * Created by hxy-company on 2018/6/16.
 */
public class SecMenu {
    private int id;
    // 模块名称
    private String title;
    // 模块的编号
    private String name;
    // 模块的优先级
    private int orderNo;

    private String path;

    private String component;

    private String ico;
    private String url;

    private int state;// 默认是否显示 0表示不显示 表示显示

    private int pid;// 父菜单id

    private String resourceType;//资源类型， 菜单 1 |接口 2

    //TODO:改成List<String> 一个页面可能要控制多个接口权限
//    private String permission; //权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view

    // 不序列化permission到前端
    @JSONField(serialize=false)
    private List<String> permission;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }


    public List<String> getPermission() {
        return permission;
    }

    public void setPermission(List<String> permission) {
        this.permission = permission;
    }
}
