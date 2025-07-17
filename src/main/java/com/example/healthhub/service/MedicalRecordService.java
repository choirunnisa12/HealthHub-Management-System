package com.example.healthhub.service;

import com.example.healthhub.entity.MedicalRecord;

import java.util.List;

public interface MedicalRecordService {
    MedicalRecord create(MedicalRecord medicalRecord);
    List<MedicalRecord> getAll();
    MedicalRecord getById(int id);
    MedicalRecord update(MedicalRecord medicalRecord, int id);
}