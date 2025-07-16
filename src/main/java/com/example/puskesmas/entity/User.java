package com.example.puskesmas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    @Size(min = 8)
    private String password;

    public enum Role {
        ADMIN,
        DOCTOR,
        NURSE,
        PATIENT,
        USER
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role = Role.USER;

    @ManyToOne
    @JoinColumn(name = "puskesmas_id") // FK mengarah ke tabel puskesmas
    private Puskesmas puskesmas;

    // relasi ke entitas lain (optional)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Doctor> doctors;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Nurse> nurses;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Patient> patients;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Billing> billings;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<MedicalRecord> medicalRecords;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Medicine> medicines;
}
