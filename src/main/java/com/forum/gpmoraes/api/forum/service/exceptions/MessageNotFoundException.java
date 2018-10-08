package com.forum.gpmoraes.api.forum.service.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MessageNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MessageNotFoundException(String msg) {
        super(msg);
    }

    public MessageNotFoundException(String msg, Throwable cause){
        super(msg,cause);
    }
}
