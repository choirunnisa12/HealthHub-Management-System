package com.example.healthhub.service;

import com.example.healthhub.entity.Doctor;

import java.util.List;

public interface DoctorService {
    Doctor create(Doctor doctor);
    List<Doctor> getAll(Doctor doctor);
    Doctor getById(int id);
    Doctor update(Doctor doctor, int id);
    void Delete(int id);
}
