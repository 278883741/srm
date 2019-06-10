package com.imooc.service;

import com.imooc.model.SysPermission;
import com.imooc.model.SysUser;

import java.util.List;

public interface UserService {
    /**
     * 添加用户
     * @param sysUser
     * @return
     */
    public boolean add(SysUser sysUser);

    /**
     * 获取用户实体
     * @param sysUser
     * @return
     */
    public SysUser get(SysUser sysUser);

    /**
     * 更新实体
     * @param sysUser
     * @return
     */
    public Integer update(SysUser sysUser);

    /**
     * 获取用户角色
     * @param userId
     * @return
     */
    public List<String> getRoleByUserId(Integer userId);

    /**
     * 根据userId查询用户所拥有的菜单
     */
    List<SysPermission> getPermissionByUserId(Integer userId);

    /**
     * 获取当前登录用户
     * @return
     */
    SysUser getLoginUser();
}
