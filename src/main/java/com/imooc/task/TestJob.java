package com.imooc.task;


import com.google.common.collect.Lists;
import com.imooc.thread.NoticeThread;
import com.imooc.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
public class TestJob {
    private  final Logger logger = LoggerFactory.getLogger(TestJob.class);

    // 这里有一个方法是10秒执行一次那种，下面是多线程写法的代替

    @Async("threadPoolTaskExecutor")
    public void Test(){
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        // 假设这里面是要执行的任务数据，比如给用户发邮件，用多线程
        // 这样线程循环读任务数据，执行完一个就继续读数据然后执行
        List<Object> list = new ArrayList<>();

        List<NoticeThread> threads = Lists.newLinkedList();
        try {
            list.forEach(item -> {
                threads.add(new NoticeThread(null,null,null));
            });
            executorService.invokeAll(threads);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
