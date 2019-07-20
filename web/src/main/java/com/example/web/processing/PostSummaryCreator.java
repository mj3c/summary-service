package com.example.web.processing;

import com.example.web.messaging.Post;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class PostSummaryCreator {
    // Regex pattern for positive lookahead of any character except the ones in brackets
    private static final Pattern endOfWordPattern = Pattern.compile("(?![a-zA-Z0-9])");

    public static Post create(String text, int length) throws SummaryCreateException {
        if (length <= 0) {
            // This should never happen since we're validating
            // the length upon request arrival, but just in case
            throw new SummaryCreateException("Length must be a positive integer");
        }
        if (length >= text.length()) {
            return new Post(text, text);
        } else {
            Matcher matcher = endOfWordPattern.matcher(text);
            if (matcher.find(length)) {
                return new Post(text, text.substring(0, matcher.start()) + "...");
            } else {
                return new Post(text, text);
            }
        }
    }
}
