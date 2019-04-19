package com.imooc.service.impl;

import com.imooc.mapper.BgmMapper;
import com.imooc.mapper.UserMapper;
import com.imooc.model.Bgm;
import com.imooc.model.User;
import com.imooc.service.BgmService;
import com.imooc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BgmServiceImpl implements BgmService {
    @Autowired
    BgmMapper bgmMapper;

    @Override
    public List<Bgm> getList(Bgm bgm) {
        return bgmMapper.select(bgm);
    }

    @Override
    public boolean add(Bgm bgm) {
        int i = bgmMapper.insertSelective(bgm);
        return i > 0;
    }
}
