package com.assignment.carmarket.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnAuthorizedHandler extends Exception {

    public UnAuthorizedHandler(String message) {
        super(message);
    }

}
