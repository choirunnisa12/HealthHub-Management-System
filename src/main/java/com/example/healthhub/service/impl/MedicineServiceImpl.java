package com.example.healthhub.service.impl;

import com.example.healthhub.entity.Medicine;
import com.example.healthhub.repository.MedicineRepository;
import com.example.healthhub.service.MedicineService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import jakarta.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {
    private final MedicineRepository medicineRepository;

    @Override
    public Medicine create(Medicine medicine) {
        return medicineRepository.saveAndFlush(medicine);
    }

    @Override
    public List<Medicine> getAll() {
        return List.of();
    }

    @Override
    public Medicine getById(int id) {
        return medicineRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Medicine with id " + id + " not found"));
    }

    @Override
    public Medicine update(Medicine medicine, int id) {
        Medicine existing = medicineRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Medicine with id " + id + " not found"));
        existing.setName(medicine.getName());
        existing.setType(medicine.getType());
        existing.setDosage(medicine.getDosage());
        existing.setExpirationDate(medicine.getExpirationDate());
        return medicineRepository.save(existing);
    }

    @Override
    public void delete(int id) {
        Medicine existing = medicineRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Medicine with id " + id + " not found"));
        medicineRepository.delete(existing);
    }
}
