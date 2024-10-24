package com.example.puskesmas.service;

import com.example.puskesmas.entity.Medicine;

import java.util.List;

public interface MedicineService {
    Medicine create (Medicine medicine);
    List<Medicine> getAll();
    Medicine getById(int id);
    Medicine update(Medicine medicine, int id);
    void delete(int id);
}
