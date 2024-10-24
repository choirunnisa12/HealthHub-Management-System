package com.example.puskesmas.service.impl;

import com.example.puskesmas.entity.Puskesmas;
import com.example.puskesmas.repository.PuskesmasRepository;
import com.example.puskesmas.service.PuskesmasService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PuskesmasServiceImpl implements PuskesmasService {
    private PuskesmasRepository puskesmasRepository;

    @Override
    public Puskesmas create(Puskesmas request) {
        return null;
    }

    @Override
    public List<Puskesmas> getAll() {
        return List.of();
    }

    @Override
    public Puskesmas getById(int id) {
        return null;
    }

    @Override
    public Puskesmas update(Puskesmas request, int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
