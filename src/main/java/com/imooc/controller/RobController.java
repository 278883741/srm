package com.imooc.controller;

import com.imooc.model.Product;
import com.imooc.service.RobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;

/**
 * 用于测试rabbitmq抢单
 */
@RestController
@RequestMapping(value = "/rob")
public class RobController {
    private static final Logger logger = LoggerFactory.getLogger(RobController.class);

    private final int threadNum = 100;
    @Autowired
    RobService robService;

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String generateMultiThread() {
//        Product product = new Product();
//        product.setProductNo(productNo);
//        product = productMapper.select(product).get(0);
        // 启动多个线程
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < threadNum; i++) {
            new Thread(new RunThread(countDownLatch,robService)).start();
        }
        countDownLatch.countDown();
        return "success";
    }
}
