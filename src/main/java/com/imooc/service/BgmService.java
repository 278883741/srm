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

    /**
     * 添加背景音乐
     * @param bgm
     * @return
     */
    public boolean add(Bgm bgm);
}
