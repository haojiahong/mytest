package com.hao.myrabbitmq2;

import com.google.common.base.Strings;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * Created by haojiahong on 17/1/18.
 */
public class NewTask {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException {
        /**
         * 创建连接连接到MabbitMQ
         */
        ConnectionFactory factory = new ConnectionFactory();
        // 设置MabbitMQ所在主机ip或者主机名
        factory.setHost("127.0.0.1");
        // 创建一个连接
        Connection connection = factory.newConnection();
        // 创建一个频道
        Channel channel = connection.createChannel();
        // 指定一个队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 发送的消息
//        String message = "Hello World...";
        // 往队列中发出一条消息
//        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
//        System.out.println(" [x] Sent '" + message + "'");

        for (int i = 0; i < 5; i++) {
            // 发送的消息
            String message = "Hello World"+ Strings.repeat(".", i);
            // 往队列中发出一条消息
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }
        // 关闭频道和连接
        channel.close();
        connection.close();
    }
}
