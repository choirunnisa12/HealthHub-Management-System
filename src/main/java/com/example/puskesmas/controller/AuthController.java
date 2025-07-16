package com.example.puskesmas.controller;

import com.example.puskesmas.dto.LoginUserDTO;
import com.example.puskesmas.dto.RegisterUserDTO;
import com.example.puskesmas.dto.response.LoginResponse;
import com.example.puskesmas.entity.User;
import com.example.puskesmas.service.AuthService;
import com.example.puskesmas.service.UserService;
import com.example.puskesmas.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid RegisterUserDTO request) {
        User newUser = userService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");

        User user = userService.getByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            // Generate JWT with role
            String token = userService.generateJwtForUser(user);
            return ResponseEntity.ok(Map.of("token", token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials"));
        }
    }
}
