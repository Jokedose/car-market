package com.assignment.carmarket.dto;

import lombok.Data;

@Data
public class ReqCreateCarProfileDTO {
    private String carBranch;
    private String carModel;
    private String carSubModel;
    private String carType;
    private String carYear;
    private String carGear;
    private Double mileage;
    private Double carScore;
    private Double carPrice;
    private Integer userAccountId;
    private String createdBy;
    private String updatedBy;
}
