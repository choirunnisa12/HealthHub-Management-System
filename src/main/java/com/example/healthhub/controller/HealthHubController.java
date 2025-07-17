package com.example.healthhub.controller;

import com.example.healthhub.entity.HealthHub;
import com.example.healthhub.service.HealthHubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/healthhub")
@RequiredArgsConstructor
public class HealthHubController {
    private final HealthHubService healthHubService;

    @PostMapping
    public HealthHub create(@RequestBody HealthHub request) {
        return healthHubService.create(request);
    }

    @GetMapping
    public List<HealthHub> getAll() {
        return healthHubService.getAll();
    }

    @GetMapping("/{id}")
    public HealthHub getById(@PathVariable Long id) {
        return healthHubService.getById(id);
    }

    @PutMapping("/{id}")
    public HealthHub update(@RequestBody HealthHub request, @PathVariable Long id) {
        return healthHubService.update(request, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        healthHubService.delete(id);
    }
}

