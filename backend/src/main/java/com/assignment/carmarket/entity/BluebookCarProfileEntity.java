package com.assignment.carmarket.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "bluebook_car_profile")
public class BluebookCarProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "car_brand")
    private String carBrand;

    @Column(name = "car_model")
    private String carModel;

    @Column(name = "car_sub_model")
    private String carSubModel;

    @Column(name = "car_type")
    private String carType;

    @Column(name = "car_year")
    private String carYear;

    @Column(name = "car_gear")
    private String carGear;
}
