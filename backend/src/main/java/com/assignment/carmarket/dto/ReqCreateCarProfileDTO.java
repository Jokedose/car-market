package com.assignment.carmarket.dto;

import lombok.Data;

@Data
public class ReqCreateCarProfileDTO {
    private String carBrand;
    private String carModel;
    private String carSubModel;
    private String carType;
    private String carYear;
    private String carGear;
    private String carColor;
    private String licensePlateNumber;
    private String licensePlateProvince;
    private Double mileage;
    private Double carScore;
    private Double carPrice;
    private Integer userAccountId;
    private String createdBy;
    private String updatedBy;
}
