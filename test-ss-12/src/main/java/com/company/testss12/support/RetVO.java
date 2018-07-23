package com.company.testss12.support;

public class RetVO {

    public RetVO() {
    }

    public RetVO(int code) {
        this.code = code;
    }

    private int code = 0;
    private String msg;
    private Object data;
    private Object page;

    public RetVO failure(String _msg) {
        this.code = -1;
        this.msg = _msg;
        return this;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
        this.page = page;
    }


}
