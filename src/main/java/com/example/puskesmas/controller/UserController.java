package com.example.puskesmas.controller;

import com.example.puskesmas.entity.User;
import com.example.puskesmas.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<User>create(@RequestBody User user){
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<User>getAll() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User>getById(@PathVariable int id){
        User user = userService.getById(id);
        if (user != null){
            return ResponseEntity.ok(user);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<User>update(@PathVariable int id, @RequestBody User user){
        return null;
    }

    @DeleteMapping
    public ResponseEntity<User>delete(@PathVariable int id){
        return null;
    }
}
