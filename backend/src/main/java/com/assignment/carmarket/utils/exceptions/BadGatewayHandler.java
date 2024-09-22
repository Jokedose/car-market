package com.assignment.carmarket.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_GATEWAY)
public class BadGatewayHandler extends Exception{

    public BadGatewayHandler(String message) {
        super(message);
    }
}

