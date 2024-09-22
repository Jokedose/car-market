package com.assignment.carmarket.repository;

import com.assignment.carmarket.entity.HealthCheckEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthCheckRepository extends JpaRepository<HealthCheckEntity, Long> {
}
