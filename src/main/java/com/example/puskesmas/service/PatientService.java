package com.example.puskesmas.service;

import com.example.puskesmas.entity.Patient;

import java.util.List;

public interface PatientService {
    Patient create(Patient request);
    List<Patient> getAll();
    Patient getById(int id);
    Patient update(Patient request, int id);
    void delete(int id);
}
