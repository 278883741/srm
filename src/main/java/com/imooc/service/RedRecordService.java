package com.imooc.service;

import com.imooc.model.RedRecord;
import com.imooc.model.SysPermission;
import com.imooc.model.SysUser;

import java.util.List;

public interface RedRecordService {
    boolean add(RedRecord redRecord);

    RedRecord get(RedRecord redRecord);

    Integer update(RedRecord redRecord);
}
