package com.example.puskesmas.service.impl;

import com.example.puskesmas.dto.LoginUserDTO;
import com.example.puskesmas.dto.response.LoginResponse;
import com.example.puskesmas.entity.User;
import com.example.puskesmas.repository.UserRepository;
import com.example.puskesmas.security.JwtUtil;
import com.example.puskesmas.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
   private final JwtUtil jwtUtils;
   private final UserRepository userRepository;

   @Override
   public LoginResponse login(LoginUserDTO request) {
       User user = userRepository.findByEmail(request.getEmail());
       if (user == null || !user.getPassword().equals(request.getPassword())) {
           throw new IllegalArgumentException("Invalid email or password");
       }
       String token = jwtUtils.generateToken(user.getUsername());
       return new LoginResponse(token);
   }
}
