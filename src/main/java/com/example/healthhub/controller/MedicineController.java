package com.example.healthhub.controller;

import com.example.healthhub.entity.Medicine;
import com.example.healthhub.service.MedicineService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicine")
@AllArgsConstructor
public class MedicineController {
    private final MedicineService medicineService;

    @PostMapping
    public ResponseEntity<Medicine>create(@RequestBody Medicine medicine){
        return new ResponseEntity(medicine, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<Medicine>getAll(){
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<Medicine>getById(@PathVariable int id) {
        Medicine medicine = medicineService.getById(id);
        if (medicine != null) {
            return ResponseEntity.ok(medicine);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<Medicine>update(@PathVariable int id, @RequestBody Medicine medicine){
        return null;
    }
    @DeleteMapping
    public ResponseEntity<Medicine>delete(@PathVariable int id){
        return null;
    }
}
