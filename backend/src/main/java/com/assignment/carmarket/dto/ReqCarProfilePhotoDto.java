package com.assignment.carmarket.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ReqCarProfilePhotoDto {
    private Long id;
    private Long carProfileId;
    private String createdBy;
    private String updatedBy;
}
