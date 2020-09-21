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
        /*
            Fanout Exchange -- 这个交换机没有路由键概念，就算你绑了路由键也是无视的。这个交换机在接收到消息后，会直接转发到绑定到它上面的所有队列。
            Topic Exchange -- 主题交换机，这个交换机其实跟直连交换机流程差不多，但是它的特点就是在它的路由键和绑定键之间是有规则的。
            *  (星号) 用来表示一个单词 (必须出现的)
            #  (井号) 用来表示任意数量（零个或多个）单词
            通配的绑定键是跟队列进行绑定的，举个小例子
            队列Q1 绑定键为 *.TT.*，队列Q2绑定键为  TT.#
            如果一条消息携带的路由键为 A.TT.B，那么队列Q1将会收到；
            如果一条消息携带的路由键为TT.AA.BB，那么队列Q2将会收到；
         */
        // 广播 - 发送给所有绑定到路由的队列，队列只需要绑定exchange即可，不需要routingKey
        // log.info();
        rabbitTemplate.setExchange("exchange_direct");
        rabbitTemplate.setRoutingKey("routingKey_direct");
        rabbitTemplate.send(MessageBuilder.withBody(message.getBytes("UTF-8")).build());
        return "success";
    }


    @RequestMapping(value = "/sendClassMessage", method = RequestMethod.POST)
    public Object sendClassMessage(@RequestBody RedRecord redRecord) throws Exception{
        rabbitTemplate.setExchange("exchange_topic");
        rabbitTemplate.setRoutingKey("topic.test");
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.convertAndSend(MessageBuilder.withBody(objectMapper.writeValueAsBytes(redRecord)).setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT)
                .build());

        return "success";
    }

    @RequestMapping(value = "/sendDeadMessage", method = RequestMethod.POST)
    public Object sendDeadMessage(@RequestBody RedRecord redRecord) throws Exception{
        rabbitTemplate.setExchange("produce_exchange_dead");
        rabbitTemplate.setRoutingKey("produce_routingKey_dead");
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.convertAndSend(MessageBuilder.withBody(objectMapper.writeValueAsBytes(redRecord)).setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT)
                .build());
        return "success";
    }
}
