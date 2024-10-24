package com.example.puskesmas.service.impl;

import com.example.puskesmas.dto.RegisterUserDTO;
import com.example.puskesmas.entity.User;
import com.example.puskesmas.repository.UserRepository;
import com.example.puskesmas.service.UserService;
import lombok.AllArgsConstructor;
//import org.springframework.securityrity.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
//    private PasswordEncoder passwordEncoder;

    @Override
    public User create(RegisterUserDTO request) {
        // Cek apakah username sudah ada
        User existingUser = userRepository.findByUsername(request.getUsername());
        if (existingUser != null) {
            throw new IllegalArgumentException("Username sudah ada");
        }

//        // Membuat User baru dan enkripsi password
        User newUser = new User();
//        newUser.setUsername(request.getUsername());
//        newUser.setPassword(passwordEncoder.encode(request.getPassword())); // Pastikan password di-hash
//        newUser.setEmail(request.getEmail());
//
        return userRepository.save(newUser);
    }
    @Override
    public List<User> getAll() {
        return List.of();
    }

    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public User update(User user, int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public boolean validateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    @Override
    public boolean validateEmail(String email, String password) {
        User user = userRepository.findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }
}
