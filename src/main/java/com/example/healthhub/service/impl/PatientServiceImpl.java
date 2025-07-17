package com.example.healthhub.service.impl;

import com.example.healthhub.entity.Patient;
import com.example.healthhub.repository.PatientRepository;
import com.example.healthhub.service.PatientService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@RequiredArgsConstructor

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    @Override
    public Patient create(Patient request) {
        return patientRepository.save(request);
    }

    @Override
    @Cacheable("patients")
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    @Override
    public Page<Patient> getAll(Pageable pageable) {
        return patientRepository.findAll(pageable);
    }

    @Override
    @Cacheable(value = "patientById", key = "#id")
    public Patient getById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient with id " + id + " not found"));
    }

    @Override
    public Patient update(Patient request, Long id) {
        Patient existing = patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient with id " + id + " not found"));
        // Update fields as needed
        existing.setName(request.getName());
        existing.setBirthDate(request.getBirthDate());
        existing.setAddress(request.getAddress());
        existing.setAge(request.getAge());
        existing.setAssignedDoctor(request.getAssignedDoctor());
        existing.setAdmittedDate(request.getAdmittedDate());
        existing.setHasAllergies(request.isHasAllergies());
        existing.setSymptoms(request.getSymptoms());
        return patientRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        Patient existing = patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient with id " + id + " not found"));
        patientRepository.delete(existing);
    }
}
