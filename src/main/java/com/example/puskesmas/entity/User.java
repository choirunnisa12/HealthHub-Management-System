package com.example.puskesmas.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
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

    @ManyToOne
    @JoinColumn(name = "puskesmas_id")
    private Puskesmas puskesmas; // Relasi ke Puskesmas

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Doctor> doctors; // Relasi ke Doctor

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Nurse> nurses; // Relasi ke Nurse

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Patient> patients; // Relasi ke Patient

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Billing> billings; // Relasi ke Billing

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<MedicalRecord> medicalRecords; // Relasi ke MedicalRecord

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Medicine> medicines; // Relasi ke Medicine
}