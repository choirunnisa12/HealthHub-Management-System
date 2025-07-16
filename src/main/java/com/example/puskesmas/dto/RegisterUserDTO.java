package com.example.puskesmas.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterUserDTO {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Email(message = "Invalid Email")
    @NotBlank
    private String email;

    private com.example.puskesmas.entity.User.Role role; // Optional, can be null (default USER)
}