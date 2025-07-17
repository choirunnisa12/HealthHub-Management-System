package com.example.healthhub.repository;

import com.example.healthhub.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Integer> , JpaSpecificationExecutor <Medicine>{
    Optional<Medicine> findById(Integer id);
}
