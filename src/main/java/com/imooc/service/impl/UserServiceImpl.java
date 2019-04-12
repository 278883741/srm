package com.imooc.service.impl;

import com.imooc.mapper.UserMapper;
import com.imooc.model.User;
import com.imooc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public boolean queryUserNameExist(String userName) {
        User user = new User();
        user.setUserName(userName);
        List<User> list = userMapper.select(user);
        if (list.size() > 0) {
            return true;
        }
        return false;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean add(User user) {
        int i = userMapper.insertSelective(user);
        return i > 0;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public User get(User user) {
        List<User> list = userMapper.select(user);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public Integer update(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
