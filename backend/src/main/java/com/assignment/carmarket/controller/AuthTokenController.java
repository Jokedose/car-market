package com.assignment.carmarket.controller;

import com.assignment.carmarket.service.AuthTokenService;
import com.assignment.carmarket.utils.ResponseHandler;
import com.assignment.carmarket.utils.exceptions.UnAuthorizedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthTokenController {
    @Autowired
    private AuthTokenService authTokenService;

    @GetMapping("/user")
    public ResponseEntity<Object> getUser(@RequestHeader("Access-Token") String accessToken) throws UnAuthorizedHandler {
        return ResponseHandler.generateResponse("Successfully get auth user.",
                HttpStatus.OK,
                this.authTokenService.getAccount(accessToken),
                ResponseHandler.STATUS_SUCCESS);
    }
}
