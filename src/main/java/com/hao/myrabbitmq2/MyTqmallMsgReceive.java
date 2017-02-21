package com.hao.myrabbitmq2;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

/**
 * Created by haojiahong on 17/2/20.
 */
public class MyTqmallMsgReceive {

    private final static String EXCHANGE = "iserver_trade_log_exchange";
    private final static String QUEUE_NAME = "iserver_trade_log_queue_hao";
    private final static String QUEUE_NAME_1 = "iserver_trade_log_queue_hao_1";
    private final static String ROUTING_KEY = "routing_key";


    public static void main(String[] args) {

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        MessageListenerAdapter pushOrderAdapter = new MessageListenerAdapter(new MyTqmallMsgHandler());
        container.setMessageListener(pushOrderAdapter);
        container.setQueueNames(QUEUE_NAME_1);
        container.setConcurrentConsumers(2);
        container.setMaxConcurrentConsumers(20);
        container.start();


    }
}
