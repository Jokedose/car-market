package com.assignment.carmarket.controller;

import com.assignment.carmarket.dto.ReqCarProfilePhotoDto;
import com.assignment.carmarket.service.CarProfilePhotoService;
import com.assignment.carmarket.utils.ResponseHandler;
import com.assignment.carmarket.utils.exceptions.BadRequestHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/car-profile-photo")
@Slf4j
public class CarProfilePhotoController {
    @Autowired
    private CarProfilePhotoService carProfilePhotoService;

    @PostMapping("upload")
    public ResponseEntity<Object> uploadCarProfilePhoto(MultipartFile file, @RequestParam(required = true) Long carProfileId, @RequestParam(required = false) Long id) throws Exception {
        log.info("POST: /api/car-profile-photo/upload with carProfileId: {} and id: {}", carProfileId, id);
        return ResponseHandler.generateResponse("Successfully upload car profile photo.",
                HttpStatus.OK,
                carProfilePhotoService.uploadCarProfilePhoto(file, carProfileId, id),
                ResponseHandler.STATUS_SUCCESS);
    }
}
