package com.example.web.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PostSender {
    @Value("${ss.rabbitmq.exchange}")
    private String exchange;

    @Value("${ss.rabbitmq.routingkey}")
    private String routingKey;

    @Autowired
    private RabbitTemplate jsonRabbitTemplate;

    public void sendPost(Post post) {
        jsonRabbitTemplate.convertAndSend(exchange, routingKey, post);
    }
}
