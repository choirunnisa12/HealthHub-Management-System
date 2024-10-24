package com.example.puskesmas.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "nurses")
@Entity
public class Nurse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDate birthDate;
    @NotNull
    @Min(10)
    @Max(13)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "puskesmas_id")
    private Puskesmas puskesmas;

    // Relasi Many-to-Many dengan Patient
    @ManyToMany
    @JoinTable(
            name = "nurse_patient",
            joinColumns = @JoinColumn(name = "nurse_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id")
    )
    private List<Patient> patients;

    // Relasi One-to-Many dengan MedicalRecord
    @OneToMany(mappedBy = "nurse", cascade = CascadeType.ALL)
    private List<MedicalRecord> medicalRecords;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}