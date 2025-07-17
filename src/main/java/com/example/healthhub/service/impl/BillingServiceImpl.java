package com.example.healthhub.service.impl;

import com.example.healthhub.dto.request.BillingDTO;
import com.example.healthhub.entity.Billing;
import com.example.healthhub.repository.BillingRepository;
import com.example.healthhub.service.BillingService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BillingServiceImpl implements BillingService {
    private final BillingRepository billingRepository;

    @Override
    public Billing create(Billing billing) {
        return billingRepository.saveAndFlush(billing);
    }

    @Override
    public List<Billing> getAll() {
        return billingRepository.findAll();
    }

    @Override
    public Billing getById(int id) {
        Optional<Billing> billing = billingRepository.findById(id);
        return billing.orElse(null);
    }

    @Override
    public Billing update(Billing billing, int id) {
        return null;
    }

    @Override
    public void delete(int id) {
    Billing billing = this.getById(id);
    if (billing==null){
        throw new EntityNotFoundException("billing with id "+ id+ "not found");
    }
    billingRepository.deleteById(id);
    }
}
