package com.imooc.service.impl;

import com.imooc.mapper.RedDetailMapper;
import com.imooc.mapper.RedRecordMapper;
import com.imooc.model.RedDetail;
import com.imooc.model.RedRecord;
import com.imooc.service.RedDetailService;
import com.imooc.service.RedRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedDetailServiceImpl implements RedDetailService {
    @Autowired
    RedDetailMapper redDetailMapper;

    @Override
    public boolean add(RedDetail redDetail) {
        int i = redDetailMapper.insertSelective(redDetail);
        return i > 0;
    }

    @Override
    public RedDetail get(RedDetail redDetail) {
        List<RedDetail> list = redDetailMapper.select(redDetail);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public Integer update(RedDetail redDetail) {
        return redDetailMapper.updateByPrimaryKeySelective(redDetail);
    }
}
