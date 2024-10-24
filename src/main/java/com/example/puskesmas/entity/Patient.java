package com.example.puskesmas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private LocalDate birthDate;
    private String address;
    private int age;
    private String assignedDoctor;
    private LocalDate admittedDate;
    private boolean hasAllergies;
    private String symptoms;
    public enum PaymentMethod {
        MANDIRI,
        ASURANSI
    }

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "nurse_id")
    private Nurse nurse;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<MedicalRecord> medicalRecordList;

    @ManyToMany
    private List<Medicine> medicines;

}

