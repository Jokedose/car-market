package com.assignment.carmarket.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SignUpAccountDTO {
    private String username;
    private String password;

    private String taxId;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
}
