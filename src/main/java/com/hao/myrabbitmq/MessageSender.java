package com.hao.myrabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by haojiahong on 16/6/7.
 */
@Component
public class MessageSender {
    @Resource(name = "myAmqpTemplate")
    AmqpTemplate amqpTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendMessage(Object message) {
        amqpTemplate.convertAndSend(message);
    }
}
