package com.example.puskesmas.service.impl;

import com.example.puskesmas.entity.Medicine;
import com.example.puskesmas.repository.MedicineRepository;
import com.example.puskesmas.service.MedicineService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return null;
    }

    @Override
    public Medicine update(Medicine medicine, int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
