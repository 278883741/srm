package com.srm.model;

import java.util.List;

public class SysPermission {
    private Integer id;
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

    private String permissionName;
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
    public String getPermissionName() {
        return permissionName;
    }

    private String permissionUrl;
    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl;
    }
    public String getPermissionUrl() {
        return permissionUrl;
    }

    private List<SysPermission> childList;
    public void setChildList(List<SysPermission> childList) {
        this.childList = childList;
    }
    public List<SysPermission> getChildList() {
        return childList;
    }
}
