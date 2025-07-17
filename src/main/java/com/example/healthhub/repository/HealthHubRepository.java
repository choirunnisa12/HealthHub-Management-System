package com.example.healthhub.repository;

import com.example.healthhub.entity.HealthHub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HealthHubRepository extends JpaRepository<HealthHub, Long>, JpaSpecificationExecutor<HealthHub> {
    Optional<HealthHub> findById(Long id);
}
