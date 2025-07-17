package com.example.healthhub.service;

import com.example.healthhub.dto.request.RegisterRequest;
import com.example.healthhub.dto.request.LoginRequest;
import com.example.healthhub.entity.User;
import java.util.List;

public interface UserService {
    User register(RegisterRequest request);
    User login(LoginRequest request);
    List<User> getAll();
    User getById(Long id);
    User create(User user);
    User update(User user, Long id);
    void delete(Long id);
}
