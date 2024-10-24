//package com.example.puskesmas.controller;
//
//import com.example.puskesmas.dto.RegisterUserDTO;
//import com.example.puskesmas.entity.User;
//import com.example.puskesmas.security.JwtUtil;
//import com.example.puskesmas.service.UserService;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//@AllArgsConstructor
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    private final UserService userService;
//    private final JwtUtil jwtUtil;
//
//    // Register user using DTO
//    @PostMapping("/register")
//    public ResponseEntity<User> register(@RequestBody RegisterUserDTO request) {
//        User newUser = userService.create(request);
//        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
//    }
//
//    // Login user using JSON body for username and password
//    @PostMapping("/login")
//    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> loginRequest) {
//        String username = loginRequest.get("username");
//        String password = loginRequest.get("password");
//
//        if (userService.validateUser(username, password)) {
//            String token = jwtUtil.generateToken(username);
//            return ResponseEntity.ok(Map.of("token", token));
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials"));
//        }
//    }
//}
