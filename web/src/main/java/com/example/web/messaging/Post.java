package com.example.web.messaging;

public class Post {
    private String text;
    private String summary;

    public Post(String text, String summary) {
        this.text = text;
        this.summary = summary;
    }

    public String getText() {
        return text;
    }

    public String getSummary() {
        return summary;
    }
}
