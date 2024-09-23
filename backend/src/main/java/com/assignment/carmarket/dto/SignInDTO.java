package com.assignment.carmarket.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SignInDTO {
    private String username;
    private String password;
}
