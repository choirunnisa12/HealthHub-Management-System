package com.example.healthhub.controller;

import com.example.healthhub.entity.Nurse;
import com.example.healthhub.service.NurseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/nurse")
public class NurseController {
    private final NurseService nurseService;

    @PostMapping
    public ResponseEntity<Nurse>create(@RequestBody Nurse nurse){
        return new ResponseEntity<>(nurse, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<Nurse>getAll(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nurse>getById(@PathVariable int id){
        Nurse nurse = nurseService.getById(id);
        if (nurse != null){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return  ResponseEntity.notFound().build();
        }
    }
    @PutMapping
    public ResponseEntity<Nurse>update(@PathVariable int id, @RequestBody Nurse nurse){
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<Nurse>delete(@PathVariable int id){
        return null;
    }
}
