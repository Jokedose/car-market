package com.assignment.carmarket.controller;

import com.assignment.carmarket.dto.ReqBluebookCarProfileDto;
import com.assignment.carmarket.service.BluebookCarProfileService;
import com.assignment.carmarket.utils.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bluebook")
@Slf4j
public class BluebookCarProfile {
    @Autowired
    private BluebookCarProfileService bluebookCarProfileService;

    @GetMapping("/car-brand")
    public ResponseEntity<Object> getCarBrand() throws Exception {
        return ResponseHandler.generateResponse("Successfully get car brand.",
                HttpStatus.OK,
                bluebookCarProfileService.getCarBrand(),
                ResponseHandler.STATUS_SUCCESS);
    }

    @PostMapping("/car-model")
    public ResponseEntity<Object> getCarModel(@RequestBody ReqBluebookCarProfileDto dto) throws Exception {
        return ResponseHandler.generateResponse("Successfully get car model.",
                HttpStatus.OK,
                bluebookCarProfileService.getCarModel(dto),
                ResponseHandler.STATUS_SUCCESS);
    }

    @PostMapping("/car-year")
    public ResponseEntity<Object> getCarYear(@RequestBody ReqBluebookCarProfileDto dto) throws Exception {
        return ResponseHandler.generateResponse("Successfully get car year.",
                HttpStatus.OK,
                bluebookCarProfileService.getCarYear(dto),
                ResponseHandler.STATUS_SUCCESS);
    }

    @PostMapping("/car-sub-model")
    public ResponseEntity<Object> getCarSubModel(@RequestBody ReqBluebookCarProfileDto dto) throws Exception {
        return ResponseHandler.generateResponse("Successfully get car sub model.",
                HttpStatus.OK,
                bluebookCarProfileService.getCarSubModel(dto),
                ResponseHandler.STATUS_SUCCESS);
    }

    @PostMapping("/car-gear")
    public ResponseEntity<Object> getCarGear(@RequestBody ReqBluebookCarProfileDto dto) throws Exception {
        return ResponseHandler.generateResponse("Successfully get car gear.",
                HttpStatus.OK,
                bluebookCarProfileService.getCarGear(dto),
                ResponseHandler.STATUS_SUCCESS);
    }
}
