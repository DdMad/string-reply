package com.beta.replyservice.model;

import org.springframework.http.HttpStatus;

public class ErrorMessage implements Message {
    private final String data;
    private final HttpStatus status;

    public ErrorMessage(String data, HttpStatus status) {
        this.data = data;
        this.status = status;
    }

    @Override
    public String getData() {
        return data;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
