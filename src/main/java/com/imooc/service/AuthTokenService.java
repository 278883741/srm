package com.imooc.service;

import com.imooc.model.AuthToken;

import java.util.List;

public interface AuthTokenService {
    boolean add(AuthToken authToken);
    boolean update(AuthToken authToken);
    AuthToken get(AuthToken authToken);
    List<AuthToken> getList(AuthToken authToken);
}
