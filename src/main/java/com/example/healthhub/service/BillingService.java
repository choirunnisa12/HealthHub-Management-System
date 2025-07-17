package com.example.healthhub.service;

import com.example.healthhub.dto.request.BillingDTO;
import com.example.healthhub.entity.Billing;

import java.util.List;

public interface BillingService {
    Billing create(Billing billing);
    List<Billing>getAll();
    Billing getById(int id);
    Billing update(Billing billing, int id);
    void delete(int id);
}
