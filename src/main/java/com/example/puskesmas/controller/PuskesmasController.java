package com.example.puskesmas.controller;

import com.example.puskesmas.entity.Puskesmas;
import com.example.puskesmas.service.PuskesmasService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/puskesmas")
@AllArgsConstructor
@RestController
public class PuskesmasController {
    private PuskesmasService puskesmasService;

    @PostMapping
    public ResponseEntity<Puskesmas>create(@RequestBody Puskesmas puskesmas){
        return new ResponseEntity<>(puskesmas, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Puskesmas>getAll(){
        return new  ResponseEntity(HttpStatus.OK);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<Puskesmas>getById(@PathVariable int id) {
        Puskesmas puskesmas = puskesmasService.getById(id);
        if (puskesmas != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping
    public ResponseEntity<Puskesmas>update(@PathVariable int id){
        return null;
    }
    @DeleteMapping
    public ResponseEntity<Puskesmas>delete(@PathVariable int id){
        return null;
    }
}

