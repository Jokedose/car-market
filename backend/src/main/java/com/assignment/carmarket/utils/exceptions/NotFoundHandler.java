package com.assignment.carmarket.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundHandler extends Exception{
    public NotFoundHandler(String message) {
        super(message);
    }
}
