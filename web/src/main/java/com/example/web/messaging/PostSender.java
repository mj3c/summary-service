package com.example.web.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PostSender {
    @Value("${ss.rabbitmq.exchange}")
    private String exchange;

    @Value("${ss.rabbitmq.routingkey}")
    private String routingKey;

    @Autowired
    private RabbitTemplate jsonRabbitTemplate;

    @Scheduled(fixedDelay = 3000L)
    public void sendPost() {
        Post post = new Post("This is a very long post", "This is a very...");
        jsonRabbitTemplate.convertAndSend(exchange, routingKey, post);
    }
}
