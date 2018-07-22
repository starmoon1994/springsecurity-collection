package com.company.testss12.entity;

import java.util.Date;

/**
 * 实体类的公共基础类 新建的业务实体一般都要继承本类
 * Created by hyp-company on 2018/6/20.
 */
public class BaseEntity {
    private long id;

    private Date createTime;

    private Date modifyTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
