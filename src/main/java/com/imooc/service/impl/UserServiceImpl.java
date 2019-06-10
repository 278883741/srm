package com.imooc.service.impl;

import com.imooc.mapper.SysUserMapper;
import com.imooc.model.SysPermission;
import com.imooc.model.SysRolePermission;
import com.imooc.model.SysUser;
import com.imooc.service.SysPermissionService;
import com.imooc.service.SysRolePermissionService;
import com.imooc.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.shiro.subject.Subject;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    SysRolePermissionService sysRolePermissionService;
    @Autowired
    SysPermissionService sysPermissionService;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean add(SysUser sysUser) {
        int i = sysUserMapper.insertSelective(sysUser);
        return i > 0;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public SysUser get(SysUser sysUser) {
        List<SysUser> list = sysUserMapper.select(sysUser);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public Integer update(SysUser sysUser) {
        return sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    @Override
    // @Cacheable(value = "users1",key = "'Roles-User-'.concat(#userId)")
    public List<String> getRoleByUserId(Integer userId) {
        return sysUserMapper.getRoleByUserId(userId);
    }

    @Override
    // @Cacheable(value = "users1",key = "'Permissions-User-'.contact(#userId)")
    public List<SysPermission> getPermissionByUserId(Integer userId) {
        List<SysPermission> permissionParent =  sysUserMapper.getPermissionByUserId(userId);
        for(SysPermission item : permissionParent){
            SysPermission sysPermission = new SysPermission();
            sysPermission.setParentId(item.getId());
            sysPermission.setLevel(2);
            item.setChildList(sysPermissionService.getList(sysPermission));
        }
        return permissionParent;
    }

    @Override
    public SysUser getLoginUser() {
        Subject subject = SecurityUtils.getSubject();
        if(subject != null){
            return  (SysUser) subject.getPrincipal();
        }
        return null;
    }
}
