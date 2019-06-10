package com.imooc.service.impl;

import com.imooc.mapper.SysPermissionMapper;
import com.imooc.mapper.SysRolePermissionMapper;
import com.imooc.model.SysPermission;
import com.imooc.model.SysRolePermission;
import com.imooc.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {
    @Autowired
    SysPermissionMapper sysPermissionMapper;

    @Override
    public boolean add(SysPermission sysPermission) {
        return false;
    }

    @Override
    public SysPermission get(SysPermission sysPermission) {
        return null;
    }

    @Override
    public List<SysPermission> getList(SysPermission sysPermission) {
        return sysPermissionMapper.select(sysPermission);
    }

    @Override
    public Integer update(SysPermission sysPermission) {
        return null;
    }
}
