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

    /*
        如下配置，当程序启动的时候并不会创建exchange01及routingKey01，当我们调用了sendSimpleMessage方法才会创建
        配置帮我们做了exchange01 - queue01 - routingKey01的绑定
        当我们发送了消息到exchange01，routingKey01之后，相关绑定的队列就自动接收到了消息
     */
    @Bean
    public DirectExchange basicExchange() {
        return new DirectExchange("exchange01", true, false);
    }

    @Bean(name = "basicQueue")
    public Queue basicQueue() {
        return new Queue("queue01", true);
    }

    @Bean
    public Binding basicBinding() {
        return BindingBuilder.bind(basicQueue()).to(basicExchange()).with("routingKey01");
    }

    @Bean(name = "simpleQueue")
    public Queue simpleQueue() {
        return new Queue("queue02", true);
    }

    @Bean
    public TopicExchange simpleExchange() {
        return new TopicExchange("exchange02", true, false);
    }

    @Bean
    public Binding simpleBinding() {
        return BindingBuilder.bind(simpleQueue()).to(simpleExchange()).with("routingKey02");
    }

    @Autowired
    private SimpleListener simpleListener;

    @Bean(name = "simpleContainer")
    public SimpleMessageListenerContainer simpleContainer(@Qualifier("simpleQueue") Queue simpleQueue) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setMessageConverter(new Jackson2JsonMessageConverter());

        //TODO：并发配置
        container.setConcurrentConsumers(5);
        container.setMaxConcurrentConsumers(10);
        container.setPrefetchCount(5);

        //TODO：消息确认-确认机制种类
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setQueues(simpleQueue);
        container.setMessageListener(simpleListener);
        return container;
    }
}