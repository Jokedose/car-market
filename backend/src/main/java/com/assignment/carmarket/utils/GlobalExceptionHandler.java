package com.assignment.carmarket.utils;

import com.assignment.carmarket.utils.exceptions.BadGatewayHandler;
import com.assignment.carmarket.utils.exceptions.BadRequestHandler;
import com.assignment.carmarket.utils.exceptions.InternalServerErrorHandler;
import com.assignment.carmarket.utils.exceptions.NotFoundHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadGatewayHandler.class)
    public ResponseEntity<Object> badGatewayExceptionHandler(BadGatewayHandler ex) {
        return  ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.BAD_GATEWAY, ResponseHandler.STATUS_ERROR);
    }

    @ExceptionHandler(BadRequestHandler.class)
    public ResponseEntity<Object> badRequestExceptionHandler(BadRequestHandler ex) {
        return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, ResponseHandler.STATUS_ERROR);
    }

    @ExceptionHandler(InternalServerErrorHandler.class)
    public ResponseEntity<Object> internalServerErrorExceptionHandler(InternalServerErrorHandler ex) {
        return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, ResponseHandler.STATUS_ERROR);
    }
    @ExceptionHandler(NotFoundHandler.class)
    public ResponseEntity<Object> NotFoundExceptionHandler(NotFoundHandler ex) {
        return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.NOT_FOUND, ResponseHandler.STATUS_ERROR);
    }


}

