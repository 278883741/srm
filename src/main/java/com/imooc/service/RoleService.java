package com.imooc.service;

import com.imooc.model.SysPermission;
import com.imooc.model.SysRole;

import java.util.List;

public interface RoleService {
    /**
     * 添加角色
     * @param sysRole
     * @return
     */
    public boolean add(SysRole sysRole);

    /**
     * 获取权限
     * @param sysRole
     * @return
     */
    public SysRole get(SysRole sysRole);

    /**
     * 获取权限列表
     * @param sysRole
     * @return
     */
    public List<SysRole> getList(SysRole sysRole);

    /**
     * 更新权限
     * @param sysRole
     * @return
     */
    public Integer update(SysRole sysRole);
}
