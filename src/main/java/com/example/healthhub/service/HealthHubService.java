package com.example.healthhub.service;

import com.example.healthhub.entity.HealthHub;
import java.util.List;

public interface HealthHubService {
    List<HealthHub> getAll();
    HealthHub getById(Long id);
    HealthHub create(HealthHub healthHub);
    HealthHub update(HealthHub healthHub, Long id);
    void delete(Long id);
}
