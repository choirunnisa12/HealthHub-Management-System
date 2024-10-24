package com.example.puskesmas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "puskesmas")
public class Puskesmas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @NotBlank
    @Column(name = "address")
    private String address;

    @NotBlank
    @Column(name = "phone_number") // Gunakan snake_case untuk konsistensi
    private String phoneNumber;

    @OneToMany(mappedBy = "puskesmas", cascade = CascadeType.ALL)
    private List<Doctor> doctorList;

    @ManyToMany
    @JoinTable(
            name = "puskesmas_users",
            joinColumns = @JoinColumn(name = "puskesmas_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users; // Pastikan ada list untuk users

    @OneToMany(mappedBy = "puskesmas", cascade = CascadeType.ALL)
    private List<Nurse> nurseList; // Hanya @OneToMany di sini

    @OneToMany(mappedBy = "puskesmas", cascade = CascadeType.ALL)
    private List<Billing> billings;

    @OneToMany(mappedBy = "puskesmas", cascade = CascadeType.ALL)
    private List<Medicine> medicines;

    @OneToMany(mappedBy = "puskesmas", cascade = CascadeType.ALL)
    private List<MedicalRecord> medicalRecords;
}
