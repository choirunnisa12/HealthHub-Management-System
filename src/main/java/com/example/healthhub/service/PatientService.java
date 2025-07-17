package com.example.healthhub.service;

import com.example.healthhub.entity.Patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PatientService {
    Patient create(Patient request);
    List<Patient> getAll();
    Page<Patient> getAll(Pageable pageable);
    Patient getById(Long id);
    Patient update(Patient request, Long id);
    void delete(Long id);
}
