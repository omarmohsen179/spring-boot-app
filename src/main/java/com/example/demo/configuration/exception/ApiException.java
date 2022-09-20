package com.example.demo.configuration.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;


public record ApiException(String message, HttpStatus httpStatus, ZonedDateTime timestamp) {
    @Override
    public String message() {
        return message;
    }

    @Override
    public HttpStatus httpStatus() {
        return httpStatus;
    }

    @Override
    public ZonedDateTime timestamp() {
        return timestamp;
    }
}
