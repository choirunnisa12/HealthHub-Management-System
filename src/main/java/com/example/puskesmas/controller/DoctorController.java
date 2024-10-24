package com.example.puskesmas.controller;

import com.example.puskesmas.entity.Doctor;
import com.example.puskesmas.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/doctors")
@RestController
@AllArgsConstructor
public class DoctorController {
    private  final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<Doctor>create (@RequestBody Doctor doctor){
        return new ResponseEntity<>(doctor, HttpStatus.CREATED);
    }
    @GetMapping
    public  ResponseEntity<Doctor>getAll(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Doctor>getById(@PathVariable int id){
        Doctor doctor = doctorService.getById(id);
        if (doctor != null){
            return ResponseEntity.ok(doctor);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping
    public ResponseEntity<Doctor>update(@RequestBody Doctor doctor, @PathVariable int id){
        return null;
    }

    @DeleteMapping
    public ResponseEntity<Doctor>delete(@PathVariable int id){
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
