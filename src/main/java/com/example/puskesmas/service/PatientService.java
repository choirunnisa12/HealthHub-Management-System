package com.example.puskesmas.service;

import com.example.puskesmas.entity.Patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PatientService {
    Patient create(Patient request);
    List<Patient> getAll();
    Page<Patient> getAll(Pageable pageable);
    Patient getById(int id);
    Patient update(Patient request, int id);
    void delete(int id);
}
