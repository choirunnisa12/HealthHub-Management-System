package com.example.puskesmas.service.impl;

import com.example.puskesmas.entity.Patient;
import com.example.puskesmas.repository.PatientRepository;
import com.example.puskesmas.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PatientServiceImpl implements PatientService {
    private PatientRepository patientRepository;

    @Override
    public Patient create(Patient request) {
        return null;
    }

    @Override
    public List<Patient> getAll() {
        return List.of();
    }

    @Override
    public Patient getById(int id) {
        return null;
    }

    @Override
    public Patient update(Patient request, int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
