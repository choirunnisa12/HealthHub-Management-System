package com.example.puskesmas;

import com.example.puskesmas.dto.RegisterUserDTO;
import com.example.puskesmas.entity.User;
import com.example.puskesmas.repository.UserRepository;
import com.example.puskesmas.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        RegisterUserDTO dto = new RegisterUserDTO();
        dto.setUsername("user1");
        dto.setPassword("pass");
        dto.setEmail("user1@mail.com");
        when(userRepository.findByUsername("user1")).thenReturn(null);
        User user = new User();
        user.setUsername("user1");
        user.setPassword("pass");
        user.setEmail("user1@mail.com");
        when(userRepository.save(any(User.class))).thenReturn(user);
        User result = userService.create(dto);
        assertNotNull(result);
        assertEquals("user1", result.getUsername());
    }

    @Test
    void testGetAll() {
        User u1 = new User();
        User u2 = new User();
        when(userRepository.findAll()).thenReturn(List.of(u1, u2));
        List<User> result = userService.getAll();
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testGetById() {
        User user = new User();
        user.setId(1);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        User result = userService.getById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdate() {
        User user = new User();
        user.setId(1);
        user.setUsername("user1");
        user.setEmail("user1@mail.com");
        user.setPassword("pass");
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
        User updated = new User();
        updated.setUsername("user2");
        updated.setEmail("user2@mail.com");
        updated.setPassword("pass2");
        User result = userService.update(updated, 1);
        assertNotNull(result);
        assertEquals("user2", result.getUsername());
    }

    @Test
    void testDelete() {
        doNothing().when(userRepository).deleteById(1);
        assertDoesNotThrow(() -> userService.delete(1));
    }

    @Test
    void testValidateUser() {
        User user = new User();
        user.setUsername("user1");
        user.setPassword("pass");
        when(userRepository.findByUsername("user1")).thenReturn(user);
        boolean valid = userService.validateUser("user1", "pass");
        assertTrue(valid);
    }

    @Test
    void testValidateEmail() {
        User user = new User();
        user.setEmail("user1@mail.com");
        user.setPassword("pass");
        when(userRepository.findByEmail("user1@mail.com")).thenReturn(user);
        boolean valid = userService.validateEmail("user1@mail.com", "pass");
        assertTrue(valid);
    }
} 