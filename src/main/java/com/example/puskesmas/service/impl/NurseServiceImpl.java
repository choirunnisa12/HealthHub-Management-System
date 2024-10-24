package com.example.puskesmas.service.impl;

import com.example.puskesmas.entity.Nurse;
import com.example.puskesmas.repository.NurseRepository;
import com.example.puskesmas.service.NurseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NurseServiceImpl implements NurseService {
    private NurseRepository nurseRepository;

    @Override
    public Nurse create(Nurse nurse) {
        return nurseRepository.saveAndFlush(nurse);
    }

    @Override
    public List<Nurse> getAll() {
        return List.of();
    }

    @Override
    public Nurse getById(int id) {
        return null;
    }

    @Override
    public Nurse update(Nurse nurse, int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
