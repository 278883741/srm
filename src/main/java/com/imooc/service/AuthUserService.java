package com.imooc.service;

import com.imooc.model.AuthUser;

import java.util.List;

public interface AuthUserService {
    boolean add(AuthUser authUser);
    boolean update(AuthUser authUser);
    AuthUser get(AuthUser authUser);
    List<AuthUser> getList(AuthUser authUser);
}
