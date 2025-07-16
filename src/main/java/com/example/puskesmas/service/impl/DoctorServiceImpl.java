package com.example.puskesmas.service.impl;

import com.example.puskesmas.entity.Doctor;
import com.example.puskesmas.repository.DoctorRepository;
import com.example.puskesmas.service.DoctorService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import jakarta.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;

    @Override
    public Doctor create(Doctor doctor) {
        return doctorRepository.saveAndFlush(doctor);
    }

    @Override
    public List<Doctor> getAll(Doctor doctor) {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getById(int id) {
        Optional<Doctor>doctor = doctorRepository.findById(id);
        return doctor.orElseThrow();
    }

    @Override
    public Doctor update(Doctor doctor, int id) {
        Doctor existing = doctorRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Doctor with id " + id + " not found"));
            existing.setDoctorName(doctor.getDoctorName());
        return doctorRepository.save(existing);
    }

    @Override
    public void Delete(int id) {

    }
}
