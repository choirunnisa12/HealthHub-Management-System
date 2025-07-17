package com.example.healthhub.service;

import com.example.healthhub.dto.LoginUserDTO;
import com.example.healthhub.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginUserDTO request);
}
