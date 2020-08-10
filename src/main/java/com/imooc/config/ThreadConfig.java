package com.imooc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 多线程配置，用到的是线程池配置
 */

@Configuration
public class ThreadConfig {
    /**
     * 1.ThreadPoolExecutor -- 继承自Executor(里面有一个execute()方法，用来执行线程)
     * 2.ThreadPoolTaskExecutor
     * 线程池主要提供一个线程队列，队列中保存着所有等待状态的线程。避免了创建与销毁的额外开销
     *
     */

    @Bean("threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        // 核心线程数
        threadPoolTaskExecutor.setCorePoolSize(4);
        // 最大线程数
        threadPoolTaskExecutor.setMaxPoolSize(8);
        // 线程池维护线程所允许的空闲时间，最多等待10秒，没数据处理就销毁线程
        threadPoolTaskExecutor.setKeepAliveSeconds(10);
        // 队列最大长度
        threadPoolTaskExecutor.setQueueCapacity(8);
        // 线程池对拒绝任务(无线程可用)的处理策略
        // AbortPolicy：用于被拒绝任务的处理程序，它将抛出RejectedExecutionException
        // CallerRunsPolicy：用于被拒绝任务的处理程序，它直接在execute方法的调用线程中运行被拒绝的任务。
        // DiscardOldestPolicy：用于被拒绝任务的处理程序，它放弃最旧的未处理请求，然后重试execute。
        // DiscardPolicy：用于被拒绝任务的处理程序，默认情况下它将丢弃被拒绝的任务。
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 数据初始化
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
