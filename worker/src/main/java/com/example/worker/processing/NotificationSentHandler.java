package com.example.worker.processing;

import io.netty.handler.codec.http.HttpHeaders;
import org.asynchttpclient.AsyncHandler;
import org.asynchttpclient.HttpResponseBodyPart;
import org.asynchttpclient.HttpResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotificationSentHandler implements AsyncHandler<Void> {
    private static final Logger logger = LoggerFactory.getLogger(NotificationSentHandler.class);

    private String message;
    private int status;

    NotificationSentHandler(String message) {
        this.message = message;
    }

    @Override
    public State onStatusReceived(HttpResponseStatus httpResponseStatus) {
        status = httpResponseStatus.getStatusCode();
        return State.ABORT;
    }

    @Override
    public State onHeadersReceived(HttpHeaders httpHeaders) {
        return State.ABORT;
    }

    @Override
    public State onBodyPartReceived(HttpResponseBodyPart httpResponseBodyPart) {
        return State.ABORT;
    }

    @Override
    public void onThrowable(Throwable throwable) { }

    @Override
    public Void onCompleted() {
        if (status == 200) {
            logger.info(message);
        }
        return null;
    }
}
