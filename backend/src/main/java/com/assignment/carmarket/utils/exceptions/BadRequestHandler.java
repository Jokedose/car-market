package com.assignment.carmarket.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestHandler extends Exception{
    public BadRequestHandler(String message) {
        super(message);
    }
}
