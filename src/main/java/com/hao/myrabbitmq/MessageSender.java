package com.hao.myrabbitmq;

import org.springframework.amqp.core.AmqpTemplate;

import javax.annotation.Resource;

/**
 * Created by haojiahong on 16/6/7.
 */
public class MessageSender {
    @Resource(name = "myAmqpTemplate")
    AmqpTemplate amqpTemplate;

    public void sendMessage(Object message) {
        amqpTemplate.convertAndSend(message);
    }
}
