package com.imooc.service;

import com.alibaba.fastjson.JSONObject;
import com.imooc.model.User;

public interface UserService {
    /**
     * 查询用户名是否存在
     * @param userName
     * @return
     */
    public boolean queryUserNameExist(String userName);

    /**
     * 添加用户
     * @param user
     * @return
     */
    public boolean add(User user);

    /**
     * 获取用户实体
     * @param user
     * @return
     */
    public User get(User user);
}
