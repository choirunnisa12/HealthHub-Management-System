package com.example.puskesmas.service.impl;

import com.example.puskesmas.entity.Doctor;
import com.example.puskesmas.repository.DoctorRepository;
import com.example.puskesmas.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DoctorServiceImpl implements DoctorService {
    private DoctorRepository doctorRepository;

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
        return null;
    }

    @Override
    public void Delete(int id) {

    }
}
