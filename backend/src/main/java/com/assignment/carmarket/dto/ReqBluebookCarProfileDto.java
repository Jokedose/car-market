package com.assignment.carmarket.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ReqBluebookCarProfileDto {
    private String carBrand;
    private String carModel;
    private String carYear;
    private String carSubModel;
    private String carType;
}
