package com.imooc.config;

import com.imooc.listener.SimpleListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitmqConfig {
    private static final Logger logger = LoggerFactory.getLogger(RabbitmqConfig.class);

    @Autowired
    private Environment env;

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Autowired
    private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;

    /**
     * 单一消费者
     *
     * @return
     */
    @Bean(name = "singleListenerContainer")
    public SimpleRabbitListenerContainerFactory listenerContainer() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setConcurrentConsumers(1);
        factory.setMaxConcurrentConsumers(1);
        // 一次获取多少个消息
        factory.setPrefetchCount(1);
        factory.setTxSize(1);
        return factory;
    }

    /**
     * 多个消费者
     *
     * @return
     */
    @Bean(name = "multiListenerContainer")
    public SimpleRabbitListenerContainerFactory multiListenerContainer() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factoryConfigurer.configure(factory, connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        // 需要手动应答，none关闭手动应答,MANUAL手动应答
        factory.setAcknowledgeMode(AcknowledgeMode.NONE);
        factory.setConcurrentConsumers(10);
        factory.setMaxConcurrentConsumers(20);
        factory.setPrefetchCount(50);
        return factory;
    }

    // 为RabbitTemplate重新配置一些属性，并做了一些全局配置
    @Bean
    public RabbitTemplate rabbitTemplate() {
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                logger.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause);
            }
        });
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                logger.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message);
            }
        });
        return rabbitTemplate;
    }

    // 直连模式
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("exchange01", true, false);
    }

    @Bean
    public Queue directQueue() {
        return new Queue("directQueue", true);
    }

    @Bean
    public Binding directBinding() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with("routingKey_direct");
    }

    // 主题模式
    @Bean
    public Queue firstQueue() {
        return new Queue("topic.firstQueue", true);
    }

    @Bean
    public Queue secondQueue() {
        return new Queue("topic.secondQueue", true);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("exchange_topic", true, false);
    }

    /*
        只要像如下这样绑定，到topic.#，那么今后发消息时候只要routingKey以topic开头，消费者都会接收到消息
     */
    @Bean
    public Binding bindingExchangeMessage1() {
        return BindingBuilder.bind(firstQueue()).to(topicExchange()).with("topic.#");
    }

    @Bean
    public Binding bindingExchangeMessage2() {
        return BindingBuilder.bind(secondQueue()).to(topicExchange()).with("topic.#");
    }

    // 发送接收消息的第二种方式，写container，写Listener
    @Autowired
    private SimpleListener simpleListener;

    @Bean(name = "simpleContainer")
    public SimpleMessageListenerContainer simpleContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setMessageConverter(new Jackson2JsonMessageConverter());

        //TODO：并发配置
        container.setConcurrentConsumers(5);
        container.setMaxConcurrentConsumers(10);
        container.setPrefetchCount(5);

        //TODO：消息确认-确认机制种类
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setQueues(directQueue());
        container.setMessageListener(simpleListener);
        return container;
    }

    // 死信队列 - 用途：比如用户下单10分钟还没支付，那我们就要先把订单放进死信队列，然后超时未支付再做后续处理
    // 数据放入死信队列（生产端），另一个队列绑定map中的路由和routingKey去消费其中的数据
    @Bean
    public Queue deadQueue(){
        Map<String, Object> args = new HashMap();

        // DLX - dead letter exchange,DLK - dead letter routingKey,TTL - 超时时间
        args.put("x-dead-letter-exchange", "exchange_dead");
        args.put("x-dead-letter-routing-key", "routingKey_dead");
        args.put("x-message-ttl", 10000);

        return new Queue("queue_dead",true,false,false,args);
    }

    @Bean
    public TopicExchange deadExchange(){
        return new TopicExchange("produce_exchange_dead",true,false);
    }

    @Bean
    public Binding bindingDeadExchange(){
        return BindingBuilder.bind(deadQueue()).to(deadExchange()).with("produce_routingKey_dead");
    }

    // 死信队列实际的消费者
    @Bean
    public Queue deadQueue_consume(){
        return new Queue("queue_deadConsume",true);
    }

    @Bean
    public Binding simpleDeadRealBinding(){
        return BindingBuilder.bind(deadQueue_consume()).to(deadExchange()).with("routingKey_dead");
    }
}