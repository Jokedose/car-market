package com.assignment.carmarket.service;

import com.assignment.carmarket.entity.HealthCheckEntity;
import com.assignment.carmarket.repository.HealthCheckRepository;
import com.assignment.carmarket.utils.exceptions.NotFoundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HealthCheckService {
    private final HealthCheckRepository healthCheckRepository;

    public HealthCheckService(HealthCheckRepository healthCheckRepository) {
        this.healthCheckRepository = healthCheckRepository;
    }

    public HealthCheckEntity getHealthCheck() throws NotFoundHandler {
        log.info("get health check.");
        return this.healthCheckRepository.findById(1L).orElseThrow(() -> new NotFoundHandler("Health check not found."));
    }
}
