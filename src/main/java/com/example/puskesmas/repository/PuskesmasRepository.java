package com.example.puskesmas.repository;

import com.example.puskesmas.entity.Puskesmas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PuskesmasRepository extends JpaRepository<Puskesmas, Long>, JpaSpecificationExecutor<Puskesmas> {
    Optional<Puskesmas> findById(Integer id);
}
