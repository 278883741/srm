package com.imooc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 多线程配置，用到的是线程池配置
 */
public class ThreadConfig {
    @Bean("threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 默认开启4个线程
        executor.setCorePoolSize(4);
        // 当数据处理不过来时最多开到8个线程
        executor.setMaxPoolSize(8);
        // 最多等待10秒，没数据处理就销毁线程
        executor.setKeepAliveSeconds(10);
        executor.setQueueCapacity(8);

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }
}
