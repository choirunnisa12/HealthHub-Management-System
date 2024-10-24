package com.example.puskesmas.service;

import com.example.puskesmas.dto.LoginUserDTO;
import com.example.puskesmas.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginUserDTO request);
}
