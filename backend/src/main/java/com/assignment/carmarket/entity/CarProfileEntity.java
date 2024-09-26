package com.assignment.carmarket.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "car_profile")
public class CarProfileEntity extends BaseColumn {
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

    @Column(name = "car_color")
    private String carColor;

    @Column(name = "license_plate_number")
    private String licensePlateNumber;

    @Column(name = "license_plate_province")
    private String licensePlateProvince;

    @Column(name = "mileage")
    private Double mileage;

    @Column(name = "car_score")
    private Double carScore;

    @Column(name = "car_price")
    private Double carPrice;

    @Column(name = "user_account_id")
    private Integer userAccountId;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "carProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarProfilePhotoEntity> carProfilePhotos;
}
