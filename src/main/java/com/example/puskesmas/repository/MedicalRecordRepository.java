package com.example.puskesmas.repository;

import com.example.puskesmas.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord,Integer>, JpaSpecificationExecutor<MedicalRecord> {
    Optional<MedicalRecord> findById(Integer id);
}
