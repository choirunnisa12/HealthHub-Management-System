package com.example.puskesmas;

import com.example.puskesmas.entity.MedicalRecord;
import com.example.puskesmas.repository.MedicalRecordRepository;
import com.example.puskesmas.service.impl.MedicalRecordServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MedicalRecordServiceImplTest {
    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @InjectMocks
    private MedicalRecordServiceImpl medicalRecordService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        MedicalRecord record = new MedicalRecord();
        record.setId(1);
        when(medicalRecordRepository.saveAndFlush(any(MedicalRecord.class))).thenReturn(record);
        MedicalRecord result = medicalRecordService.create(record);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testGetAll() {
        MedicalRecord r1 = new MedicalRecord();
        MedicalRecord r2 = new MedicalRecord();
        when(medicalRecordRepository.findAll()).thenReturn(List.of(r1, r2));
        List<MedicalRecord> result = medicalRecordService.getAll();
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testGetById() {
        MedicalRecord record = new MedicalRecord();
        record.setId(1);
        when(medicalRecordRepository.findById(1)).thenReturn(Optional.of(record));
        MedicalRecord result = medicalRecordService.getById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }
} 