package com.imooc.controller;


import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/sendSimpleMessage", method = RequestMethod.GET)
    public Object sendSimpleMessage(@RequestParam String message) throws Exception{
        // 广播 - 发送给所有绑定到路由的队列，队列只需要绑定exchange即可，不需要routingKey

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
}
