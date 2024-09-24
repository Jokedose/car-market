package com.assignment.carmarket.controller;

import com.assignment.carmarket.dto.ReqCreateCarProfileDTO;
import com.assignment.carmarket.dto.ReqFilterCarProfileDTO;
import com.assignment.carmarket.service.CarProfileService;
import com.assignment.carmarket.utils.EncryptionUtils;
import com.assignment.carmarket.utils.ResponseHandler;
import com.assignment.carmarket.utils.exceptions.BadRequestHandler;
import com.assignment.carmarket.utils.exceptions.NotFoundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/car-profile")
@Slf4j
public class CarProfileController {
    @Autowired
    private CarProfileService carProfileService;

    @GetMapping("")
    public ResponseEntity<Object> getAllCarProfile(ReqFilterCarProfileDTO filter) throws Exception {
        log.info("GET: /api/car-profile with request: {}", filter);
        return ResponseHandler.generateResponse("Successfully get all car profile.",
                HttpStatus.OK,
                this.carProfileService.getAllCarProfile(filter),
                ResponseHandler.STATUS_SUCCESS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) throws Exception {
        log.info("GET: /api/car-profile/{}", id);
        return ResponseHandler.generateResponse("Successfully get car profile by id.",
                HttpStatus.OK,
                this.carProfileService.getCarProfileById(id),
                ResponseHandler.STATUS_SUCCESS);
    }

    @PostMapping("")
    public ResponseEntity<Object> createCarProfile(@RequestBody ReqCreateCarProfileDTO dto) throws BadRequestHandler {
        log.info("POST: /api/car-profile with request: {}", dto);
        return ResponseHandler.generateResponse("Successfully create car profile.",
                HttpStatus.OK,
                this.carProfileService.createCarProfile(dto),
                ResponseHandler.STATUS_SUCCESS);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateCarProfileById(@PathVariable Long id, @RequestBody ReqCreateCarProfileDTO dto) throws BadRequestHandler, NotFoundHandler {
        log.info("PATCH: /api/car-profile/{} with request: {}", id, dto);
        return ResponseHandler.generateResponse("Successfully update car profile by id.",
                HttpStatus.OK,
                this.carProfileService.updateCarProfile(id, dto),
                ResponseHandler.STATUS_SUCCESS);
    }
}
