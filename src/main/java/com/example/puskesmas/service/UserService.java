package com.example.puskesmas.service;

import com.example.puskesmas.dto.RegisterUserDTO;
import com.example.puskesmas.entity.User;

import java.util.List;

public interface UserService {
    User create(RegisterUserDTO request);
    List<User> getAll();
    User getById(int id);
    User update(User user, int id);
    void delete(int id);
    boolean validateUser(String username, String password); // Tambahkan metode ini
    boolean validateEmail(String email, String password); // Tambahkan metode ini

}
