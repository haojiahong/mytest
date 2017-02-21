package com.hao.myrabbitmq2;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
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
    private final static String QUEUE_NAME_1 = "iserver_trade_log_queue_hao_1";
    private final static String ROUTING_KEY = "routing_key";


    public static void main(String[] args) throws IOException {

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");

        //这里定义了一个转发器directExchange，两个队列queue，queue1，一个绑定建ROUTING_KEY。
        //将这两个队列通过绑定键帮顶到了转发器上。在管理后台确实看到了两个队列生成。
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        DirectExchange directExchange = new DirectExchange(EXCHANGE);
        Queue queue = new Queue(QUEUE_NAME);
        Queue queue1 = new Queue(QUEUE_NAME_1);
        Binding binding = BindingBuilder.bind(queue).to(directExchange).with(ROUTING_KEY);
        Binding binding1 = BindingBuilder.bind(queue1).to(directExchange).with(ROUTING_KEY);

        rabbitAdmin.declareExchange(directExchange);
        rabbitAdmin.declareQueue(queue);
        rabbitAdmin.declareQueue(queue1);
        rabbitAdmin.declareBinding(binding);
        rabbitAdmin.declareBinding(binding1);

//        Connection connection = connectionFactory.createConnection();

        RabbitTemplate template = new RabbitTemplate(connectionFactory);
//        Message message = MessageBuilder.withBody("1111".getBytes())
//                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
//                .build();
//        template.send(QUEUE_NAME, message);
//        template.convertAndSend(QUEUE_NAME, "111111");
        template.convertAndSend(EXCHANGE, ROUTING_KEY, "111333334444");

    }
}
