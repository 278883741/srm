package com.imooc.service.impl;

import com.imooc.mapper.RedRobRecordMapper;
import com.imooc.model.RedRobRecord;
import com.imooc.service.RedRobRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedRobRecordServiceImpl implements RedRobRecordService {
    @Autowired
    RedRobRecordMapper redRobRecordMapper;

    @Override
    public boolean add(RedRobRecord redRobRecord) {
        int i = redRobRecordMapper.insertSelective(redRobRecord);
        return i > 0;
    }

    @Override
    public RedRobRecord get(RedRobRecord redRobRecord) {
        List<RedRobRecord> list = redRobRecordMapper.select(redRobRecord);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public Integer update(RedRobRecord redRobRecord) {
        return redRobRecordMapper.updateByPrimaryKeySelective(redRobRecord);
    }
}
