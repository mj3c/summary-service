package com.example.web.processing;

import com.example.web.messaging.Post;
import org.junit.Test;

import static org.junit.Assert.*;

public class PostSummaryCreatorTest {
    @Test
    public void create_LengthInTheMiddleOfWord_TrimmedCorrectly() {
        String text = "This is a blog post that will be trimmed right here, after the word 'here', with trailing ellipsis.";
        int length = 48;
        Post post = PostSummaryCreator.create(text, length);
        assertEquals(post.getText(), text);
        assertEquals(post.getSummary(), "This is a blog post that will be trimmed right here...");
    }

    @Test
    public void create_LengthJustBeforeWord_TrimmedCorrectly() {
        String text = "This is a blog post that will be trimmed right here, exactly after the word 'right', with trailing ellipsis.";
        int length = 46;
        Post post = PostSummaryCreator.create(text, length);
        assertEquals(post.getText(), text);
        assertEquals(post.getSummary(), "This is a blog post that will be trimmed right...");
    }

    @Test
    public void create_LengthJustAfterWord_TrimmedCorrectly() {
        String text = "This is a blog post that will be trimmed right here, after the word 'here', with trailing ellipsis.";
        int length = 51;
        Post post = PostSummaryCreator.create(text, length);
        assertEquals(post.getText(), text);
        assertEquals(post.getSummary(), "This is a blog post that will be trimmed right here...");
    }

    @Test
    public void create_LengthLongerThanText_DontTrim() {
        String text = "This is a blog post that won't be trimmed, because 'length' is larger than text length.";
        int length = 1000;
        Post post = PostSummaryCreator.create(text, length);
        assertEquals(post.getText(), text);
        assertEquals(post.getSummary(), text);
    }

    @Test(expected = SummaryCreateException.class)
    public void create_LengthZero_ThrowsException() {
        String text = "This is a blog post that won't be trimmed, because of length 0.";
        int length = 0;
        PostSummaryCreator.create(text, length);
    }

    @Test(expected = SummaryCreateException.class)
    public void create_LengthNegative_ThrowsException() {
        String text = "This is a blog post that won't be trimmed, because of negative length.";
        int length = -1;
        PostSummaryCreator.create(text, length);
    }
}
