package com.imooc.thread;

import com.imooc.model.Notice;
import com.imooc.model.SysUser;
import com.imooc.service.EmailService;
import java.util.concurrent.Callable;

public class NoticeThread implements Callable<Boolean> {
    private SysUser sysUser;
    private Notice notice;
    private EmailService emailService;

    public NoticeThread(SysUser sysUser, Notice notice, EmailService emailService) {
        this.sysUser = sysUser;
        this.notice = notice;
        this.emailService = emailService;
    }

    @Override
    public Boolean call() throws Exception {
        emailService.emailUserNotice(notice,sysUser);
        return true;
    }
}
