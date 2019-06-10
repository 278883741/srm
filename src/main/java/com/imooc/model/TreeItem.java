package com.imooc.model;

import java.util.HashMap;
import java.util.Map;

public class TreeItem {
    private boolean success;
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public boolean isSuccess() {
        return success;
    }

    private String msg;
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getMsg() {
        return msg;
    }

    private Object obj;
    public void setObj(Object obj) {
        this.obj = obj;
    }
    public Object getObj() {
        return obj;
    }

    private Map<String, Object> attributes = new HashMap<String, Object>();
    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
    public Map<String, Object> getAttributes() {
        return attributes;
    }
}
