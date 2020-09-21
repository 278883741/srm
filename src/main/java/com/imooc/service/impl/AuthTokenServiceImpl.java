package com.imooc.service.impl;

import com.imooc.mapper.AuthTokenMapper;
import com.imooc.model.AuthToken;
import com.imooc.service.AuthTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthTokenServiceImpl implements AuthTokenService {
    @Autowired
    AuthTokenMapper authTokenMapper;

    @Override
    public boolean add(AuthToken authToken) {
        return authTokenMapper.insertSelective(authToken) > 0;
    }

    @Override
    public boolean update(AuthToken authToken) {
        return authTokenMapper.updateByPrimaryKeySelective(authToken) > 0;
    }

    @Override
    public AuthToken get(AuthToken authToken) {
        List<AuthToken> list = this.getList(authToken);
        if(list != null && list.size() >0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<AuthToken> getList(AuthToken authToken) {
        return authTokenMapper.select(authToken);
    }
}
