package com.example.puskesmas.service.impl;

import com.example.puskesmas.entity.Billing;
import com.example.puskesmas.repository.BillingRepository;
import com.example.puskesmas.service.BillingService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BillingServiceImpl implements BillingService {
    private BillingRepository billingRepository;

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
