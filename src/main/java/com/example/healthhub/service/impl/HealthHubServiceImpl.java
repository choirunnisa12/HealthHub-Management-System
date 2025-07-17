package com.example.healthhub.service.impl;

import com.example.healthhub.entity.HealthHub;
import com.example.healthhub.repository.HealthHubRepository;
import com.example.healthhub.service.HealthHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthHubServiceImpl implements HealthHubService {
    @Autowired
    private HealthHubRepository healthHubRepository;

    @Override
    public List<HealthHub> getAll() {
        return healthHubRepository.findAll();
    }

    @Override
    public HealthHub getById(Long id) {
        return healthHubRepository.findById(id).orElse(null);
    }

    @Override
    public HealthHub create(HealthHub healthHub) {
        return healthHubRepository.save(healthHub);
    }

    @Override
    public HealthHub update(HealthHub healthHub, Long id) {
        HealthHub existing = healthHubRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setName(healthHub.getName());
            existing.setAddress(healthHub.getAddress());
            // tambahkan field lain jika ada
            return healthHubRepository.save(existing);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        healthHubRepository.deleteById(id);
    }
}
