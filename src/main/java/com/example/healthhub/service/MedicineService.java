package com.example.healthhub.service;

import com.example.healthhub.entity.Medicine;

import java.util.List;

public interface MedicineService {
    Medicine create (Medicine medicine);
    List<Medicine> getAll();
    Medicine getById(int id);
    Medicine update(Medicine medicine, int id);
    void delete(int id);
}
