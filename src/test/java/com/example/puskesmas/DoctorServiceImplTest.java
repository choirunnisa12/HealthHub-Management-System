package com.example.puskesmas;

import com.example.puskesmas.entity.Doctor;
import com.example.puskesmas.repository.DoctorRepository;
import com.example.puskesmas.service.impl.DoctorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DoctorServiceImplTest {
    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorServiceImpl doctorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Doctor doctor = Doctor.builder().doctorName("Dr. Smith").address("Berlin").phoneNumber(123456).email("smith@hospital.com").build();
        when(doctorRepository.saveAndFlush(any(Doctor.class))).thenReturn(doctor);
        Doctor result = doctorService.create(doctor);
        assertNotNull(result);
        assertEquals("Dr. Smith", result.getDoctorName());
    }

    @Test
    void testGetAll() {
        Doctor doctor1 = Doctor.builder().doctorName("Dr. Smith").address("Berlin").phoneNumber(123456).email("smith@hospital.com").build();
        Doctor doctor2 = Doctor.builder().doctorName("Dr. Jane").address("Munich").phoneNumber(654321).email("jane@hospital.com").build();
        when(doctorRepository.findAll()).thenReturn(List.of(doctor1, doctor2));
        List<Doctor> result = doctorService.getAll(null);
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testGetById() {
        Doctor doctor = Doctor.builder().id(1).doctorName("Dr. Smith").address("Berlin").phoneNumber(123456).email("smith@hospital.com").build();
        when(doctorRepository.findById(1)).thenReturn(Optional.of(doctor));
        Doctor result = doctorService.getById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }
} 