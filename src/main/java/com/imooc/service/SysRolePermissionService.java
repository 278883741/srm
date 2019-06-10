package com.imooc.service;

import com.imooc.model.SysPermission;
import com.imooc.model.SysRolePermission;

import java.util.List;

public interface SysRolePermissionService {
    /**
     * 为角色添加权限
     * @param sysRolePermission
     * @return
     */
    boolean add(SysRolePermission sysRolePermission);

    /**
     * 获取角色拥有的权限
     * @param sysRolePermission
     * @return
     */
    SysPermission get(SysRolePermission sysRolePermission);

    /**
     * 获取角色拥有的权限列表
     * @param sysRolePermission
     * @return
     */
    List<SysRolePermission> getList(SysRolePermission sysRolePermission);

    /**
     * 更新角色拥有的权限
     * @param sysRolePermission
     * @return
     */
    Integer update(SysRolePermission sysRolePermission);

    /**
     * 删除角色拥有的权限
     * @param sysRolePermission
     * @return
     */
    Integer delete(SysRolePermission sysRolePermission);

    /**
     * 获取角色拥有的权限
     * @param roleId
     * @return
     */
    List<SysRolePermission> getByRoleId(Integer roleId);

    /**
     * 删除角色拥有的权限
     * @param roleId
     * @return
     */
    void deleteByRoleId(Integer roleId);
}
