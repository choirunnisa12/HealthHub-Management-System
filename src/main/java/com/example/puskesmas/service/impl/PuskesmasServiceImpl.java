package com.example.puskesmas.service.impl;

import com.example.puskesmas.entity.Puskesmas;
import com.example.puskesmas.repository.PuskesmasRepository;
import com.example.puskesmas.service.PuskesmasService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import jakarta.persistence.EntityNotFoundException;

@AllArgsConstructor
@Service
public class PuskesmasServiceImpl implements PuskesmasService {
    private PuskesmasRepository puskesmasRepository;

    @Override
    public Puskesmas create(Puskesmas request) {
        return puskesmasRepository.save(request);
    }

    @Override
    public List<Puskesmas> getAll() {
        return puskesmasRepository.findAll();
    }

    @Override
    public Puskesmas getById(int id) {
        return puskesmasRepository.findById(id).orElse(null);
    }

    @Override
    public Puskesmas update(Puskesmas request, int id) {
        Puskesmas existing = puskesmasRepository.findById((long) id)
            .orElseThrow(() -> new EntityNotFoundException("Puskesmas with id " + id + " not found"));
        existing.setName(request.getName());
        existing.setAddress(request.getAddress());
        existing.setPhoneNumber(request.getPhoneNumber());
        // ... update other fields as needed
        return puskesmasRepository.save(existing);
    }

    @Override
    public void delete(int id) {
        Puskesmas existing = puskesmasRepository.findById((long) id)
            .orElseThrow(() -> new EntityNotFoundException("Puskesmas with id " + id + " not found"));
        puskesmasRepository.delete(existing);
    }
}
