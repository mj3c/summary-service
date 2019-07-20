package com.example.worker.messaging;

public class Post {
    private String text;
    private String summary;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "Text: " + text + " ||| Summary: " + summary;
    }
}
