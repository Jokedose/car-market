package com.assignment.carmarket.repository;

import com.assignment.carmarket.entity.BluebookCarProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BluebookCarProfileRepository extends JpaRepository<BluebookCarProfileEntity, Long> {
    @Query(value = "SELECT DISTINCT car_brand FROM bluebook_car_profile", nativeQuery = true)
    List<String> findDistinctCarBranch();

    @Query(value = "SELECT DISTINCT car_model FROM bluebook_car_profile WHERE car_brand = :carBrand", nativeQuery = true)
    List<String> findDistinctCarModel(String carBrand);

    @Query(value = "SELECT DISTINCT car_year FROM bluebook_car_profile WHERE car_brand = :carBrand and car_model = :carModel", nativeQuery = true)
    List<String> findDistinctCarYear(String carBrand, String carModel);

    @Query(value = "SELECT DISTINCT car_sub_model FROM bluebook_car_profile WHERE car_brand = :carBrand and car_model = :carModel and car_year = :carYear", nativeQuery = true)
    List<String> findDistinctSubModel(String carBrand, String carModel, String carYear);

    @Query(value = "SELECT * FROM bluebook_car_profile WHERE car_brand = :carBrand and car_model = :carModel and car_year = :carYear and car_sub_model = :carSubModel", nativeQuery = true)
    List<BluebookCarProfileEntity> findDistinctCarGear(String carBrand, String carModel, String carYear, String carSubModel);
}
