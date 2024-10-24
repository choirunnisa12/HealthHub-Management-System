package com.example.puskesmas.controller;

import com.example.puskesmas.entity.Billing;
import com.example.puskesmas.service.BillingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/billings")
@AllArgsConstructor
public class BillingController {
    private final BillingService billingService;

    @PostMapping
    public ResponseEntity<Billing> create(@RequestBody Billing billing) {
        return new ResponseEntity<>(billing, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Billing> getAll() {
        List<Billing> billings = billingService.getAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Billing> getById(@PathVariable int id) {
        Billing billing = billingService.getById(id);
        if (billing != null) {
            return ResponseEntity.ok(billing);
        } else {
            return  ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        if (billingService.getById(id) != null) {
            billingService.delete(id);
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
