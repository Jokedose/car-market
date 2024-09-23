package com.assignment.carmarket.controller;

import com.assignment.carmarket.dto.SignInDTO;
import com.assignment.carmarket.dto.SignOutDTO;
import com.assignment.carmarket.dto.SignUpAccountDTO;
import com.assignment.carmarket.service.AccountService;
import com.assignment.carmarket.service.SignInService;
import com.assignment.carmarket.service.SignOutService;
import com.assignment.carmarket.utils.ResponseHandler;
import com.assignment.carmarket.utils.exceptions.BadRequestHandler;
import com.assignment.carmarket.utils.exceptions.InternalServerErrorHandler;
import com.assignment.carmarket.utils.exceptions.NotFoundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
@Slf4j
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private SignInService signInService;
    @Autowired
    private SignOutService signOutService;

    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUpAccount(@RequestBody SignUpAccountDTO dto) throws BadRequestHandler {
        log.info("POST: /api/account/sign-up with Request: {}", dto);
        return ResponseHandler.generateResponse("Successfully get auth user.",
                HttpStatus.OK,
                this.accountService.signUpAccount(dto),
                ResponseHandler.STATUS_SUCCESS);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Object> signIn(@RequestBody SignInDTO dto) throws InternalServerErrorHandler, BadRequestHandler {
        log.info("POST: /api/account/sign-in with Request: {}", dto);
        return ResponseHandler.generateResponse("Successfully get auth user.",
                HttpStatus.OK,
                this.signInService.signIn(dto),
                ResponseHandler.STATUS_SUCCESS);
    }

    @PostMapping("/sign-out")
    public ResponseEntity<Object> signOut(@RequestBody SignOutDTO dto) throws NotFoundHandler {
        log.info("POST: /api/account/sign-out with Request: {}", dto);
        this.signOutService.signOut(dto);
        return ResponseHandler.generateResponse("Successfully signed out.", HttpStatus.OK, ResponseHandler.STATUS_SUCCESS);
    }
}
