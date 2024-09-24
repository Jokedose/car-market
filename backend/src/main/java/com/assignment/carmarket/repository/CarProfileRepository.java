package com.assignment.carmarket.repository;

import com.assignment.carmarket.entity.CarProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarProfileRepository extends JpaRepository<CarProfileEntity, Long> {
}
