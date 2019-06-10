package com.imooc.task;


import com.imooc.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class TestJob {
    private  final Logger logger = LoggerFactory.getLogger(TestJob.class);

//    @Scheduled(cron = "0 */59 * * * ?")
//    @Async
    public void Test(){
        String html = HttpUtils.doGet("http://localhost:8080/ServiceAgent/index");
        String html1 = HttpUtils.doGet("http://localhost:8084/ServiceAgent/index");
        logger.info(new Date().toString());
    }
}
