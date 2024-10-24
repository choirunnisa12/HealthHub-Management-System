package com.example.puskesmas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doctors")
@Builder
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    @Column(name ="doctor_Name", nullable = false)
    private String doctorName;
    @NotBlank
    @Column(name = "address")
    private String address;
    @NotBlank
    @Column(name ="phone_Number", unique = true)
    private int phoneNumber;
    @NotBlank
    @Column(name ="email", unique = true)
    private String email;
    @Column(name = "specialization")
    private String specialization;
    @Column(name = "schedule")
    private String Schedule;

    @ManyToOne
    @JoinColumn(name = "puskesmas_id")
    private Puskesmas puskesmas;

    @ManyToMany
    @JoinTable(
            name = "doctor_patient",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id")
    )
    private List<Patient> patients;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<MedicalRecord> medicalRecords;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
