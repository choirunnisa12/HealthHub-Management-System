package com.example.puskesmas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medical_record")
@Builder
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "prescription")
    private String prescription;
    @Column(name ="record_Date")
    private LocalDateTime recordDate;
    @Column(name = "treatment")
    private String treatment;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
