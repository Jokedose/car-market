package com.assignment.carmarket.repository;

import com.assignment.carmarket.entity.CarProfileEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarProfileRepository extends JpaRepository<CarProfileEntity, Long> {
    Page<CarProfileEntity> findAll(Specification<CarProfileEntity> specification, Pageable pageable);
}
