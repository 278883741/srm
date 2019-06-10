package com.imooc.service.impl;

import com.imooc.mapper.SysRoleMapper;
import com.imooc.model.SysRole;
import com.imooc.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    SysRoleMapper sysRoleMapper;

    @Override
    public boolean add(SysRole sysRole) {
        return false;
    }

    @Override
    public SysRole get(SysRole sysRole) {
        return null;
    }

    @Override
    public List<SysRole> getList(SysRole sysRole) {
        return sysRoleMapper.select(sysRole);
    }

    @Override
    public Integer update(SysRole sysRole) {
        return null;
    }
}
