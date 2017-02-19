package com.hao.myrabbitmq2;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Date;

/**
 * Created by haojiahong on 17/1/19.
 */
public class ReceiveLogs2File {
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] argv) throws IOException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        // 打开连接和创建频道，与发送端一样
        Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        // 声明一个随机队列
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "");
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        // 创建队列消费者
        final Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
//                print2File(message);
                System.out.println(" [x] Received '" + message + "'");

                try {

                    Thread.sleep(10000);

                } catch (InterruptedException e) {
                } finally {
                    System.out.println(" [x] Done! at " + new Date().toLocaleString());
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };
        channel.basicConsume(queueName, false, consumer);
    }

}
