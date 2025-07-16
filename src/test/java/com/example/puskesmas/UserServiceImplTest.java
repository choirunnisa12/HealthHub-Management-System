package com.example.puskesmas;

import com.example.puskesmas.dto.RegisterUserDTO;
import com.example.puskesmas.entity.User;
import com.example.puskesmas.repository.UserRepository;
import com.example.puskesmas.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        passwordEncoder = new BCryptPasswordEncoder();
        userService = new UserServiceImpl(userRepository, passwordEncoder);
    }

    @Test
    void createUser_successfullyHashesPasswordAndSavesUser() {
        // Arrange: Prepare a new user registration DTO
        RegisterUserDTO dto = new RegisterUserDTO();
        dto.setUsername("testuser");
        dto.setPassword("password123");
        dto.setEmail("test@example.com");

        when(userRepository.findByUsername("testuser")).thenReturn(null);
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        when(userRepository.save(userCaptor.capture())).thenAnswer(invocation -> invocation.getArgument(0));

        // Act: Call the create method
        User created = userService.create(dto);

        // Assert: The password should be hashed and user saved
        assertEquals("testuser", created.getUsername());
        assertEquals("test@example.com", created.getEmail());
        assertTrue(passwordEncoder.matches("password123", created.getPassword()));
        verify(userRepository).save(any(User.class));
    }

    @Test
    void createUser_throwsExceptionIfUsernameExists() {
        // Arrange: Simulate existing user
        RegisterUserDTO dto = new RegisterUserDTO();
        dto.setUsername("existing");
        dto.setPassword("password");
        dto.setEmail("existing@example.com");
        when(userRepository.findByUsername("existing")).thenReturn(new User());

        // Act & Assert: Should throw exception
        Exception ex = assertThrows(IllegalArgumentException.class, () -> userService.create(dto));
        assertEquals("Username already exists", ex.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void deleteUser_callsRepositoryDeleteById() {
        // Arrange: Prepare a user id
        int userId = 42;

        // Act: Call delete
        userService.delete(userId);

        // Assert: Repository deleteById should be called
        verify(userRepository).deleteById(userId);
    }
} 