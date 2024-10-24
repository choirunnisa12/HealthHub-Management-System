package com.example.puskesmas.controller;

import com.example.puskesmas.entity.MedicalRecord;
import com.example.puskesmas.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicalrecords")
public class MedicalRecordController {
    @Autowired
    private MedicalRecordService medicalRecordService;

    @PostMapping
    public ResponseEntity<MedicalRecord>create(@RequestBody MedicalRecord medicalRecord){
        return new ResponseEntity<>(medicalRecord, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<MedicalRecord>getAll(){
        return new ResponseEntity<>(HttpStatus.OK );
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<MedicalRecord>getById(@PathVariable int id){
        MedicalRecord medicalRecord = medicalRecordService.getById(id);
        if (medicalRecord != null){
            return ResponseEntity.ok(medicalRecord);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<MedicalRecord>update(@RequestBody MedicalRecord medicalRecord, @PathVariable int id){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
