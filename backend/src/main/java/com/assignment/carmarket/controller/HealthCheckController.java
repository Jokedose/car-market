package com.assignment.carmarket.controller;

import com.assignment.carmarket.repository.HealthCheckRepository;
import com.assignment.carmarket.service.HealthCheckService;
import com.assignment.carmarket.utils.ResponseHandler;
import com.assignment.carmarket.utils.exceptions.NotFoundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HealthCheckController {
    private final HealthCheckService healthCheckService;

    public HealthCheckController(HealthCheckService healthCheckService) {
        this.healthCheckService = healthCheckService;
    }

    @GetMapping("health-check")
    public ResponseEntity<Object> getHealthCheck() throws NotFoundHandler {
        log.info("GET: /health-check");
        return ResponseHandler.generateResponse("Successfully get health check.",
                HttpStatus.OK,
                this.healthCheckService.getHealthCheck(),
                ResponseHandler.STATUS_SUCCESS);
    }
}
