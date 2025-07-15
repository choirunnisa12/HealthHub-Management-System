package com.example.puskesmas;

import com.example.puskesmas.entity.Billing;
import com.example.puskesmas.repository.BillingRepository;
import com.example.puskesmas.service.impl.BillingServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BillingServiceImplTest {
    @Mock
    private BillingRepository billingRepository;

    @InjectMocks
    private BillingServiceImpl billingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Billing billing = new Billing();
        billing.setId(1);
        when(billingRepository.saveAndFlush(any(Billing.class))).thenReturn(billing);
        Billing result = billingService.create(billing);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testGetAll() {
        Billing b1 = new Billing();
        Billing b2 = new Billing();
        when(billingRepository.findAll()).thenReturn(List.of(b1, b2));
        List<Billing> result = billingService.getAll();
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testGetById() {
        Billing billing = new Billing();
        billing.setId(1);
        when(billingRepository.findById(1)).thenReturn(Optional.of(billing));
        Billing result = billingService.getById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testDeleteNotFound() {
        when(billingRepository.findById(99)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> billingService.delete(99));
    }
} 