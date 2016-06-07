package com.hao.mytest.test;

import com.google.common.collect.Maps;
import com.hao.myrabbitmq.MessageSender;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by haojiahong on 16/6/7.
 */
public class RabbitMQTest {
    private ApplicationContext context = null;
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("application-context.xml");
    }

    @Test
    public void should_send_a_amq_message() throws Exception {
        MessageSender messageSender = (MessageSender) context.getBean("messageSender");
        messageSender.sendMessage("Hello, I am amq sender haojiahong");
    }

    @Test
    public void mqTest() throws Exception {
        try {
            ConnectionFactory cf = new ConnectionFactory();

            //1、设置ip、端口、用户名、密码、虚拟主机
            cf.setHost("127.0.0.1");
            cf.setVirtualHost("/");
            cf.setUsername("guest");
            cf.setPassword("guest");
            cf.setPort(5672);

            //2、创建连接
            Connection connection = cf.newConnection();

            //3、创建channel
            Channel channel = connection.createChannel();

            //4、声明队列,队列已经存在时，此时不能改变原有队列的属性，否则会报错，如此处的
            //durable,  exclusive,  autoDelete要和创建的队列保持一致
            channel.queueDeclare("com.hao.test", true, false, false, null);

            //5、把队列跟交换机通过routingKey绑定
            channel.queueBind("com.hao.test", "myChange", "hello");

            //5、发送消息
            channel.basicPublish("myChange", "hello", MessageProperties.PERSISTENT_BASIC,
                    "hello world!!!!haojiahong".getBytes());

            channel.close();

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
