package com.imooc.service;

import com.imooc.model.RedRobRecord;

public interface RedRobRecordService {
    boolean add(RedRobRecord redRobRecord);

    RedRobRecord get(RedRobRecord redRobRecord);

    Integer update(RedRobRecord redRobRecord);
}
