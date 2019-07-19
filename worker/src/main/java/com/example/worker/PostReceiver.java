package com.example.worker;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PostReceiver {
    @RabbitListener(queues = "${ss.rabbitmq.queue}")
    public void receivePost(Post post) {
        System.out.println(post);
    }
}
