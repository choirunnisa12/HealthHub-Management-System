package com.example.puskesmas.service.impl;

import com.example.puskesmas.dto.RegisterUserDTO;
import com.example.puskesmas.entity.User;
import com.example.puskesmas.repository.UserRepository;
import com.example.puskesmas.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.example.puskesmas.security.JwtUtil;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public User create(RegisterUserDTO request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            log.warn("Username '{}' already exists, cannot create user", request.getUsername());
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        // Set role from DTO if provided, otherwise default to USER
        if (request.getRole() != null) {
            user.setRole(request.getRole());
        } else {
            user.setRole(User.Role.USER);
        }
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        log.info("Creating new user with username: {}", request.getUsername());
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    public User update(User user, int id) {
        User existingUser = getById(id);
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        existingUser.setRole(user.getRole());

        log.info("Updating user with id: {}", id);
        return userRepository.save(existingUser);
    }

    @Override
    public void delete(int id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }

        log.info("Deleting user with id: {}", id);
        userRepository.deleteById(id);
    }

    @Override
    public boolean validateUser(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        return optionalUser.isPresent() &&
                passwordEncoder.matches(password, optionalUser.get().getPassword());
    }

    @Override
    public boolean validateEmail(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.isPresent() && passwordEncoder.matches(password, optionalUser.get().getPassword());
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    public String generateJwtForUser(User user) {
        // Generate JWT with username and role
        return jwtUtil.generateToken(user.getUsername(), user.getRole().name());
    }
}
