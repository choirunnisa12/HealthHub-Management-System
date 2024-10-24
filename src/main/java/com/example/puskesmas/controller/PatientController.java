package com.example.puskesmas.controller;

import com.example.puskesmas.entity.Billing;
import com.example.puskesmas.entity.Patient;
import com.example.puskesmas.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

            @PostMapping
    public ResponseEntity<Patient> create(@RequestBody Patient patient){
        return new ResponseEntity<>(patient, HttpStatus.CREATED);
            }

            @GetMapping
    public ResponseEntity<Patient> getAll(){
        return new ResponseEntity<>(HttpStatus.OK);
            }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Patient> getById(@PathVariable int id) {
        Patient patient = patientService.getById(id);
        if (patient != null) {
            return ResponseEntity.ok(patient);
        } else {
            return  ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<Patient>update(@PathVariable int id, @RequestBody Patient patient){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Patient>delete(@PathVariable int id){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
