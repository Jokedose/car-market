package com.assignment.carmarket.dto;

import lombok.Data;

@Data
public class ReqCreateFlowerDTO {
    private String name;
    private String description;
    private Double price;
    private Integer qualityAvailable;
    private Integer userAccountId;
    private String createdBy;
    private String updatedBy;
}
