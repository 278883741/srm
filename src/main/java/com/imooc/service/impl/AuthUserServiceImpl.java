package com.imooc.service.impl;

import com.imooc.mapper.AuthUserMapper;
import com.imooc.model.AuthUser;
import com.imooc.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthUserServiceImpl implements AuthUserService {
    @Autowired
    AuthUserMapper authUserMapper;

    @Override
    public boolean add(AuthUser authUser) {
        return authUserMapper.insertSelective(authUser) > 0;
    }

    @Override
    public boolean update(AuthUser authUser) {
        return authUserMapper.updateByPrimaryKeySelective(authUser) > 0;
    }

    @Override
    public AuthUser get(AuthUser authUser) {
        List<AuthUser> list = this.getList(authUser);
        if(list != null && list.size() >0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<AuthUser> getList(AuthUser authUser) {
        return authUserMapper.select(authUser);
    }
}
