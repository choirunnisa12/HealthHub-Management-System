package com.example.healthhub.repository;

import com.example.healthhub.entity.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NurseRepository extends JpaRepository<Nurse, Integer> , JpaSpecificationExecutor<Nurse> {
    Optional<Nurse> findById(Integer id);

}
