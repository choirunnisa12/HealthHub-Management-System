package com.example.puskesmas.service;

import com.example.puskesmas.dto.request.BillingDTO;
import com.example.puskesmas.entity.Billing;

import java.util.List;

public interface BillingService {
    Billing create(Billing billing);
    List<Billing>getAll();
    Billing getById(int id);
    Billing update(Billing billing, int id);
    void delete(int id);
}
