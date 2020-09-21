package com.imooc.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.model.RedRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = "topic.secondQueue")
public class TopicSecondReceiver {
    @Autowired
    ObjectMapper objectMapper;

    private static final Logger logger = LoggerFactory.getLogger(TopicSecondReceiver.class);

    @RabbitHandler
    public void process(byte[] message) {
        RedRecord redRecord = null;
        try {
            redRecord = objectMapper.readValue(message, RedRecord.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("2接收到消息:{}",redRecord);
    }
}
