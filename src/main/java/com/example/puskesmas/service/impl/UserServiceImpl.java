package com.example.puskesmas.service.impl;

import com.example.puskesmas.dto.RegisterUserDTO;
import com.example.puskesmas.entity.User;
import com.example.puskesmas.repository.UserRepository;
import com.example.puskesmas.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User create(RegisterUserDTO request) {
        // Check if the username already exists in the database
        User existingUser = userRepository.findByUsername(request.getUsername());
        if (existingUser != null) {
            logger.warn("Username '{}' already exists, cannot create new user", request.getUsername());
            throw new IllegalArgumentException("Username already exists");
        }

        // Create a new User with the password hashed for security
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword())); // Hash the password before saving
        newUser.setEmail(request.getEmail());

        logger.info("Creating new user with username: {}", request.getUsername());
        return userRepository.save(newUser);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User update(User user, int id) {
        // Find the existing user by id
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Update user data
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        // If the password is updated, hash it again for security
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
            logger.info("Password for user with id {} updated and re-hashed", id);
        }

        logger.info("Updating user data with id {}", id);
        return userRepository.save(existingUser);
    }

    @Override
    public void delete(int id) {
        logger.info("Deleting user with id {}", id);
        userRepository.deleteById(id);
    }

    @Override
    public boolean validateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public boolean validateEmail(String email, String password) {
        User user = userRepository.findByEmail(email);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }
}
