package com.imooc.controller;

import com.imooc.mapper.ProductMapper;
import com.imooc.mapper.ProductRobbingRecordMapper;
import com.imooc.model.Product;
import com.imooc.model.ProductRobbingRecord;
import com.imooc.service.RobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class RunThread implements Runnable {

    private CountDownLatch countDownLatch;
    private RobService robService;
    private static int mobile;

    public RunThread(CountDownLatch countDownLatch,RobService robService) {
        this.countDownLatch = countDownLatch;
        this.robService = robService;
    }

    @Override
    public void run(){
        try {
            countDownLatch.await();
            mobile++;
            // 不基于rabbitMq的
            robService.rob(String.valueOf(mobile));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
