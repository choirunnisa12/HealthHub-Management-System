package com.example.puskesmas.repository;

import com.example.puskesmas.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> , JpaSpecificationExecutor<User> {
    User findByUsername(String username);
    User findByEmail(String email);

}
