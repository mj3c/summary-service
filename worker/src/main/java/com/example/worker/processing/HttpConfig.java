package com.example.worker.processing;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.asynchttpclient.Dsl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpConfig {
    @Bean
    public AsyncHttpClient httpClient() {
        DefaultAsyncHttpClientConfig clientConfig = Dsl.config()
                .setUserAgent("Worker")
                .build();
        return Dsl.asyncHttpClient(clientConfig);
    }
}
