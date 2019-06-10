package com.imooc.service;

import com.imooc.model.SysPermission;
import com.imooc.model.SysRolePermission;

import java.util.List;

public interface SysPermissionService {
    /**
     * 添加权限
     * @param sysPermission
     * @return
     */
    boolean add(SysPermission sysPermission);

    /**
     * 获取权限
     * @param sysPermission
     * @return
     */
    SysPermission get(SysPermission sysPermission);

    /**
     * 获取权限列表
     * @param sysPermission
     * @return
     */
    List<SysPermission> getList(SysPermission sysPermission);

    /**
     * 更新权限
     * @param sysPermission
     * @return
     */
    Integer update(SysPermission sysPermission);
}
