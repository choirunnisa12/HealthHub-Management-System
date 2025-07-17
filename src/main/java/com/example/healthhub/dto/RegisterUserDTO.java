package com.example.healthhub.dto;

import lombok.Data;

@Data
public class RegisterUserDTO {
    private String username;
    private String email;
    private String password;
    private String role;
}