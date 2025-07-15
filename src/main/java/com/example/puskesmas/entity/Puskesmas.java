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

    @Column(name = "address")
    @NotBlank
    private String address;

    @Column(name = "phone_number")
    @NotBlank
    private String phoneNumber;

    // ✅ Satu puskesmas bisa punya banyak user
    @OneToMany(mappedBy = "puskesmas", cascade = CascadeType.ALL)
    private List<User> users;

    @OneToMany(mappedBy = "puskesmas", cascade = CascadeType.ALL)
    private List<Doctor> doctorList;

    @OneToMany(mappedBy = "puskesmas", cascade = CascadeType.ALL)
    private List<Nurse> nurseList;

    @OneToMany(mappedBy = "puskesmas", cascade = CascadeType.ALL)
    private List<Billing> billings;

    @OneToMany(mappedBy = "puskesmas", cascade = CascadeType.ALL)
    private List<Medicine> medicines;

    @OneToMany(mappedBy = "puskesmas", cascade = CascadeType.ALL)
    private List<MedicalRecord> medicalRecords;
}
