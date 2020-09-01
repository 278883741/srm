package com.imooc.service.impl;

import com.imooc.mapper.RedRecordMapper;
import com.imooc.mapper.SysUserMapper;
import com.imooc.model.RedRecord;
import com.imooc.model.SysPermission;
import com.imooc.model.SysUser;
import com.imooc.service.RedRecordService;
import com.imooc.service.SysPermissionService;
import com.imooc.service.SysRolePermissionService;
import com.imooc.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RedRecordServiceImpl implements RedRecordService {
    @Autowired
    RedRecordMapper redRecordMapper;

    @Override
    public boolean add(RedRecord redRecord) {
        int i = redRecordMapper.insertSelective(redRecord);
        return i > 0;
    }

    @Override
    public RedRecord get(RedRecord redRecord) {
        List<RedRecord> list = redRecordMapper.select(redRecord);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public Integer update(RedRecord redRecord) {
        return redRecordMapper.updateByPrimaryKeySelective(redRecord);
    }
}
