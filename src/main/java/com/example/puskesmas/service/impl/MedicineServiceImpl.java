package com.example.puskesmas.service.impl;

import com.example.puskesmas.entity.Medicine;
import com.example.puskesmas.repository.MedicineRepository;
import com.example.puskesmas.service.MedicineService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import jakarta.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class MedicineServiceImpl implements MedicineService {
    private MedicineRepository medicineRepository;

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
        // Update fields as needed
        existing.setName(medicine.getName());
        existing.setType(medicine.getType());
        existing.setDosage(medicine.getDosage());
        existing.setExpirationDate(medicine.getExpirationDate());
        // ... update other fields as needed
        return medicineRepository.save(existing);
    }

    @Override
    public void delete(int id) {
        Medicine existing = medicineRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Medicine with id " + id + " not found"));
        medicineRepository.delete(existing);
    }
}
