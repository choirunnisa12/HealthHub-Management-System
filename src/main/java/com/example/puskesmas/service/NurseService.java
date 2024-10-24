package com.example.puskesmas.service;

import com.example.puskesmas.entity.Nurse;

import java.util.List;

public interface NurseService {
    Nurse create (Nurse nurse);
    List<Nurse> getAll();
    Nurse getById(int id);
    Nurse update(Nurse nurse, int id);
    void delete(int id);
}
