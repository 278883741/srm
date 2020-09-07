package com.imooc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.model.RedRecord;
import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
@RequestMapping(value = "/consumer")
public class ConsumerController {
    @Autowired
    ObjectMapper objectMapper;

    private static final Logger logger = LoggerFactory.getLogger(ConsumerController.class);

    @RequestMapping(value = "/consumerFanoutMessage", method = RequestMethod.GET)
    public Object add() throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String exchangeName = "exchange:01";
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.FANOUT);
        String queueName = "queue:01";
        channel.queueDeclare(queueName,true,true,false,null);
        channel.queueBind(queueName,exchangeName,"");

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"UTF-8");
                logger.info("接收到新消息：{}",message);
            }
        };
        channel.basicConsume(queueName,true,consumer);

        channel.close();
        connection.close();
        return "success";
    }

    @RabbitListener(queues = "queue01",containerFactory = "singleListenerContainer")
    public void consumeMessage(@Payload byte[] message){
        try {
            RedRecord redRecord = objectMapper.readValue(message,RedRecord.class);
            logger.info("接收到消息:{}",redRecord);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
