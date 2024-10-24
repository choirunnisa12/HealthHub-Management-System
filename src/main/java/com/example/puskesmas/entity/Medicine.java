package com.example.puskesmas.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "medicines")
@Entity
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "dosage")
    private String dosage;
    @Column(name = "expiration_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;

    // Relasi Many-to-One dengan Puskesmas
    @ManyToOne
    @JoinColumn(name = "puskesmas_id")
    private Puskesmas puskesmas;

    // Relasi One-to-Many dengan MedicineRecord
    @OneToMany(mappedBy = "medicine", cascade = CascadeType.ALL)
    private List<MedicalRecord> medicalRecords;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}