package com.assignment.carmarket.dto;

import lombok.Data;

@Data
public class ReqFilterCarProfileDTO {
    private Integer pageNo;
    private Integer pageSize;
    private Long userAccountId;
    private String sortBy;
}
