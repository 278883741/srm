package com.imooc.service.impl;

import com.imooc.mapper.SysPermissionMapper;
import com.imooc.mapper.SysRolePermissionMapper;
import com.imooc.model.SysPermission;
import com.imooc.model.SysRolePermission;
import com.imooc.service.SysPermissionService;
import com.imooc.service.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRolePermissionServiceImpl implements SysRolePermissionService {
    @Autowired
    SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public boolean add(SysRolePermission sysRolePermission) {
        return sysRolePermissionMapper.insert(sysRolePermission) > 0;
    }

    @Override
    public SysPermission get(SysRolePermission sysRolePermission) {
        return null;
    }

    @Override
    public List<SysRolePermission> getList(SysRolePermission sysRolePermission) {
        return sysRolePermissionMapper.select(sysRolePermission);
    }

    @Override
    public Integer update(SysRolePermission sysRolePermission) {
        return null;
    }

    @Override
    public Integer delete(SysRolePermission sysRolePermission) {
        return sysRolePermissionMapper.delete(sysRolePermission);
    }

    @Override
    public List<SysRolePermission> getByRoleId(Integer roleId) {
        SysRolePermission sysRolePermission = new SysRolePermission();
        sysRolePermission.setRoleId(roleId);
        List<SysRolePermission> list = sysRolePermissionMapper.select(sysRolePermission);
        return list;
    }

    @Override
    public void deleteByRoleId(Integer roleId) {
        SysRolePermission sysRolePermission = new SysRolePermission();
        sysRolePermission.setRoleId(roleId);
        List<SysRolePermission> list = sysRolePermissionMapper.select(sysRolePermission);
        for (SysRolePermission item:list) {
            this.delete(item);
        }
    }
}
