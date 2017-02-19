package com.hao.myrabbitmq2;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.io.IOException;

/**
 * Created by haojiahong on 17/2/16.
 */
public class MyTqmallMsgSender {
    private final static String EXCHANGE = "iserver_trade_log_exchange";
    private final static String QUEUE_NAME = "iserver_trade_log_queue_hao";

    public static void main(String[] args) throws IOException {

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");


        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
//        TopicExchange topicExchange = new TopicExchange(EXCHANGE);
        DirectExchange directExchange = new DirectExchange(EXCHANGE);
        rabbitAdmin.declareExchange(directExchange);
//        Binding binding = new Binding();
//        rabbitAdmin.declareBinding(binding);

//        Connection connection = connectionFactory.createConnection();

        RabbitTemplate template = new RabbitTemplate(connectionFactory);
//        Message message = MessageBuilder.withBody("1111".getBytes())
//                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
//                .build();
//        template.send(QUEUE_NAME, message);
        template.convertAndSend(QUEUE_NAME, "111111");
        template.convertAndSend(EXCHANGE, "", "11133333");
        /**
         * 创建连接连接到MabbitMQ
         */
//        ConnectionFactory factory = new ConnectionFactory();
//        // 设置MabbitMQ所在主机ip或者主机名
//        factory.setHost("127.0.0.1");
//        // 创建一个连接
//        Connection connection = factory.newConnection();
//        // 创建一个频道
//        Channel channel = connection.createChannel();
//        // 指定一个队列
//        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//        // 发送的消息
//        String message = "hello world!龙轩555";
//        // 往队列中发出一条消息
//        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
//        System.out.println(" [x] Sent '" + message + "'");
//        // 关闭频道和连接
//        channel.close();
//        connection.close();
    }
}
