package com.imooc.service;

import com.imooc.model.Notice;
import com.imooc.model.SysUser;

public interface EmailService {
    void emailUserNotice(Notice notice, SysUser sysUser);


}
