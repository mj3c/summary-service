package com.example.worker.processing;

import com.example.worker.messaging.Post;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.asynchttpclient.AsyncHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationSender {
    @Value("${ss.slack.hook}")
    private String slackHook;

    @Autowired
    private AsyncHttpClient httpClient;

    private ObjectMapper objectMapper = new ObjectMapper();

    public void send(Post post) {
        Map<String, String> message = new HashMap<>();
        message.put("text", "New post published!\n*Summary*: " + post.getSummary());
        try {
            httpClient
                    .preparePost(slackHook)
                    .setHeader("Content-Type", "application/json")
                    .setBody(objectMapper.writeValueAsString(message))
                    .execute(new NotificationSentHandler(post.getSummary()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
