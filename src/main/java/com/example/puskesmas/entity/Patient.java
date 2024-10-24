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

    @ManyToMany
    @JoinTable(
            name = "patient_doctor",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    private List<Doctor> doctors;

    @ManyToOne
    @JoinColumn(name = "nurse_id")
    private Nurse nurse;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<MedicalRecord> medicalRecordList;

    @ManyToMany
    private List<Medicine> medicines;

    @ManyToOne
    @JoinColumn(name = "puskesmas_id")
    private Puskesmas puskesmas;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}

