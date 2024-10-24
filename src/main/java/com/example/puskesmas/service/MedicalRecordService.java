package com.example.puskesmas.service;

import com.example.puskesmas.entity.MedicalRecord;

import java.util.List;

public interface MedicalRecordService {
    MedicalRecord create(MedicalRecord medicalRecord);
    List<MedicalRecord> getAll();
    MedicalRecord getById(int id);
    MedicalRecord update(MedicalRecord medicalRecord, int id);
}