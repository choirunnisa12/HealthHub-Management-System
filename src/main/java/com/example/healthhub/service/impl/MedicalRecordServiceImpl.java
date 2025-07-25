package com.example.healthhub.service.impl;

import com.example.healthhub.entity.MedicalRecord;
import com.example.healthhub.repository.MedicalRecordRepository;
import com.example.healthhub.service.MedicalRecordService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import jakarta.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class MedicalRecordServiceImpl implements MedicalRecordService {
   private final MedicalRecordRepository medicalRecordRepository;

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
        MedicalRecord existing = medicalRecordRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("MedicalRecord with id " + id + " not found"));
        return medicalRecordRepository.save(existing);
    }
}
