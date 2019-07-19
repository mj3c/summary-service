package com.example.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PostReceiver {
    private static final Logger logger = LoggerFactory.getLogger(PostReceiver.class);

    @RabbitListener(queues = "${ss.rabbitmq.queue}")
    public void receivePost(Post post) {
        logger.info(post.toString());
    }
}
