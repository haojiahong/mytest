package com.hao.myrabbitmq2;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * Created by haojiahong on 17/2/23.
 */
public class TestTqmallMsgSender {
    public static void main(String[] args) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost("121.40.30.150");
        connectionFactory.setVirtualHost("tqmall");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("shop-test");
        connectionFactory.setPassword("5540200");

        RabbitTemplate template = new RabbitTemplate(connectionFactory);

        template.convertAndSend("tqmall-exchanges", "trade_log_queue", "11122334456789");
    }

}
