package com.assignment.carmarket.repository;

import com.assignment.carmarket.entity.CarProfilePhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarProfilePhotoRepository extends JpaRepository<CarProfilePhotoEntity, Long> {
    Optional<CarProfilePhotoEntity> findFirstByCarProfileId(Long carProfileId);
}
