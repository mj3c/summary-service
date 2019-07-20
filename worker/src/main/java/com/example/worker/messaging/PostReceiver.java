package com.example.worker.messaging;

import com.example.worker.persistence.PostDocument;
import com.example.worker.processing.NotificationSender;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class PostReceiver {
    @Autowired
    private NotificationSender notificationSender;

    @Autowired
    private MongoTemplate mongoTemplate;

    @RabbitListener(queues = "${ss.rabbitmq.queue}")
    public void receivePost(Post post) {
        notificationSender.send(post);
        mongoTemplate.insert(new PostDocument(post.getText(), post.getSummary()));
    }
}
