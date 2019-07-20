package com.example.worker.messaging;

import com.example.worker.processing.NotificationSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostReceiver {
    private static final Logger logger = LoggerFactory.getLogger(PostReceiver.class);

    @Autowired
    private NotificationSender notificationSender;

    @RabbitListener(queues = "${ss.rabbitmq.queue}")
    public void receivePost(Post post) {
        notificationSender.send(post);
    }
}
