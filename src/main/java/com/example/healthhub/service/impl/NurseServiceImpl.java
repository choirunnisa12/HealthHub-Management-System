package com.example.healthhub.service.impl;

import com.example.healthhub.entity.Nurse;
import com.example.healthhub.repository.NurseRepository;
import com.example.healthhub.service.NurseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import jakarta.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class NurseServiceImpl implements NurseService {
    private final NurseRepository nurseRepository;

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
        return nurseRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Nurse with id " + id + " not found"));
    }

    @Override
    public Nurse update(Nurse nurse, int id) {
        Nurse existing = nurseRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Nurse with id " + id + " not found"));
        // Update fields as needed
        existing.setName(nurse.getName());
        // ... update other fields as needed
        return nurseRepository.save(existing);
    }

    @Override
    public void delete(int id) {
        Nurse existing = nurseRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Nurse with id " + id + " not found"));
        nurseRepository.delete(existing);
    }
}
