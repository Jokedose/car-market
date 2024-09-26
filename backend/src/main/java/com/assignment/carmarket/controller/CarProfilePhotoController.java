package com.assignment.carmarket.controller;

import com.assignment.carmarket.dto.ReqCarProfilePhotoDto;
import com.assignment.carmarket.entity.CarProfilePhotoEntity;
import com.assignment.carmarket.service.CarProfilePhotoService;
import com.assignment.carmarket.utils.ResponseHandler;
import com.assignment.carmarket.utils.exceptions.BadRequestHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/car-profile-photo")
@Slf4j
public class CarProfilePhotoController {
    @Autowired
    private CarProfilePhotoService carProfilePhotoService;

    @PostMapping("upload")
    public ResponseEntity<Object> uploadCarProfilePhoto(@RequestBody ReqCarProfilePhotoDto dto) throws Exception {
        log.info("POST: /api/car-profile-photo/upload with request {}", dto);
        return ResponseHandler.generateResponse("Successfully upload car profile photo.",
                HttpStatus.OK,
                carProfilePhotoService.uploadCarProfilePhoto(dto),
                ResponseHandler.STATUS_SUCCESS);
    }
}
