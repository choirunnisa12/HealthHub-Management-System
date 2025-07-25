package com.example.healthhub.repository;

import com.example.healthhub.entity.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Integer>, JpaSpecificationExecutor<Billing> {
    Optional<Billing> findById(Integer id);
}
