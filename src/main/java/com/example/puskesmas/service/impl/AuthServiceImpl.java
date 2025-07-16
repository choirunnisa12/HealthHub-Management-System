package com.example.puskesmas.service.impl;

import com.example.puskesmas.dto.LoginUserDTO;
import com.example.puskesmas.dto.response.LoginResponse;
import com.example.puskesmas.entity.User;
import com.example.puskesmas.repository.UserRepository;
import com.example.puskesmas.security.JwtUtil;
import com.example.puskesmas.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public LoginResponse login(LoginUserDTO request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        // Generate JWT with username and role
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());
        LoginResponse response = new LoginResponse();
        response.setAccess_token(token);
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole().name());
        return response;
    }

}