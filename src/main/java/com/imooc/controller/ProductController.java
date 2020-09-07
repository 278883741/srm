package com.imooc.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.model.RedRecord;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/product")
@Slf4j
public class ProductController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    ObjectMapper objectMapper;

    @RequestMapping(value = "/sendSimpleMessage", method = RequestMethod.GET)
    public Object sendSimpleMessage(@RequestParam String message) throws Exception{
        // 广播 - 发送给所有绑定到路由的队列，队列只需要绑定exchange即可，不需要routingKey
        // log.info();
        rabbitTemplate.setExchange("exchange01");
        rabbitTemplate.setRoutingKey("routingKey01");
        rabbitTemplate.send(MessageBuilder.withBody(message.getBytes("UTF-8")).build());

//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("127.0.0.1");
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//        String exchangeName = "exchange:01";
//
//        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.FANOUT);
//
//        String message = "FANOUT的一个消息";
//        channel.basicPublish(exchangeName,"",null,message.getBytes("UTF-8"));
//
//        channel.close();
//        connection.close();
        return "success";
    }


    @RequestMapping(value = "/sendClassMessage", method = RequestMethod.POST)
    public Object sendClassMessage(@RequestBody RedRecord redRecord) throws Exception{
        rabbitTemplate.setExchange("exchange01");
        rabbitTemplate.setRoutingKey("routingKey01");
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.convertAndSend(MessageBuilder.withBody(objectMapper.writeValueAsBytes(redRecord)).setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT)
                .build());

        return "success";
    }
}
