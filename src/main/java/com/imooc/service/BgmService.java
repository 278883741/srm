package com.imooc.service;

import com.imooc.model.Bgm;
import com.imooc.model.User;

import java.util.List;

public interface BgmService {
    /**
     * 获取列表
     * @return
     */
    public List<Bgm> getList(Bgm bgm);
}
