package com.example.puskesmas.service.impl;

import com.example.puskesmas.entity.MedicalRecord;
import com.example.puskesmas.repository.MedicalRecordRepository;
import com.example.puskesmas.service.MedicalRecordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MedicalRecordServiceImpl implements MedicalRecordService {
   private MedicalRecordRepository medicalRecordRepository;

    @Override
    public MedicalRecord create(MedicalRecord medicalRecord) {
        return medicalRecordRepository.saveAndFlush(medicalRecord);

    }

    @Override
    public List<MedicalRecord> getAll() {
        return medicalRecordRepository.findAll();
    }

    @Override
    public MedicalRecord getById(int id) {
        Optional<MedicalRecord> medicalRecord = medicalRecordRepository.findById(id);
        return medicalRecord.orElseThrow();
    }

    @Override
    public MedicalRecord update(MedicalRecord medicalRecord, int id) {
        return null;
    }
}
