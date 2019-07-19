package com.example.web;

public class Post {
    private String text;
    private String summary;

    Post(String text, String summary) {
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
