package com.imooc.service;

import com.imooc.mapper.ProductMapper;
import com.imooc.mapper.ProductRobbingRecordMapper;
import com.imooc.model.Product;
import com.imooc.model.ProductRobbingRecord;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
public class RobService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductRobbingRecordMapper productRobbingRecordMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void rob(String mobile) {
        String productNo = "product_10010";

        Product product = new Product();
        product.setProductNo(productNo);
        product = productMapper.select(product).get(0);
        if (product.getTotal() > 0) {
            if (productMapper.updateTotal(productNo) > 0) {
                ProductRobbingRecord productRobbingRecord = new ProductRobbingRecord();
                productRobbingRecord.setMobile(mobile);
                productRobbingRecord.setProductId(product.getId());
                productRobbingRecordMapper.insertSelective(productRobbingRecord);
            }
        }
    }

    public void robWithRabbitMQ(String mobile) {
        try {
            rabbitTemplate.setExchange("exchange01");
            rabbitTemplate.setRoutingKey("routingKey01");
            Message message = MessageBuilder.withBody(mobile.getBytes("UTF-8")).setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                    .build();
            rabbitTemplate.send(message);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
