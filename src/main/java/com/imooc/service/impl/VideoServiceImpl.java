package com.imooc.service.impl;

import com.imooc.mapper.VideoMapper;
import com.imooc.model.BasePageModel;
import com.imooc.model.Video;
import com.imooc.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    VideoMapper videoMapper;

    @Override
    public boolean add(Video video) {
        int i = videoMapper.insertSelective(video);
        return i > 0;
    }

    @Override
    public List<Video> getList(Video video) {
        return videoMapper.select(video);
    }

    @Override
    public BasePageModel getListByPage(Integer pageIndex, Integer pageSize) {
        return null;
    }
}
