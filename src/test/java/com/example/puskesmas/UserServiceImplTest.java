package com.example.puskesmas;

import com.example.puskesmas.dto.RegisterUserDTO;
import com.example.puskesmas.entity.User;
import com.example.puskesmas.repository.UserRepository;
import com.example.puskesmas.security.JwtUtil;
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

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        passwordEncoder = new BCryptPasswordEncoder();
        userService = new UserServiceImpl(userRepository, passwordEncoder, jwtUtil);
    }

    @Test
    void createUser_successfullyHashesPasswordAndSavesUser() {
        RegisterUserDTO dto = new RegisterUserDTO();
        dto.setUsername("testuser");
        dto.setPassword("password123");
        dto.setEmail("test@example.com");

        when(userRepository.existsByUsername("testuser")).thenReturn(false);
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        when(userRepository.save(userCaptor.capture())).thenAnswer(invocation -> invocation.getArgument(0));

        User created = userService.create(dto);

        assertEquals("testuser", created.getUsername());
        assertEquals("test@example.com", created.getEmail());
        assertTrue(passwordEncoder.matches("password123", created.getPassword()));
        verify(userRepository).save(any(User.class));
    }

    @Test
    void createUser_throwsExceptionIfUsernameExists() {
        RegisterUserDTO dto = new RegisterUserDTO();
        dto.setUsername("existing");
        dto.setPassword("password");
        dto.setEmail("existing@example.com");

        when(userRepository.existsByUsername("existing")).thenReturn(true); // ✅

        RuntimeException ex = assertThrows(RuntimeException.class, () -> userService.create(dto));
        assertEquals("Username already exists", ex.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }


    @Test
    void deleteUser_callsRepositoryDeleteById() {
        // Arrange: Prepare a user id
        int userId = 42;
        when(userRepository.existsById(userId)).thenReturn(true);
        // Act: Call delete
        userService.delete(userId);
        // Assert: Repository deleteById should be called
        verify(userRepository).deleteById(userId);
    }
} 