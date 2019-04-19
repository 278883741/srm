package com.imooc.service;

import com.imooc.model.BasePageModel;
import com.imooc.model.Video;

import java.util.List;
import java.util.Map;

public interface VideoService {
    /**
     * 添加视频
     */
    public boolean add(Video video);

    /**
     * 获取用户上传的视频列表
     */
    public List<Video> getList(Video video);

    public BasePageModel getListByPage(Integer pageIndex,Integer pageSize);
}
