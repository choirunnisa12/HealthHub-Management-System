package com.example.healthhub.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Entity
@Table(name = "doctors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String specialization;

    @ManyToOne
    @JoinColumn(name = "healthhub_id")
    private HealthHub healthHub;

    @ManyToMany(mappedBy = "doctors")
    private List<Patient> patients;

    public String getDoctorName() { return name; }
    public void setDoctorName(String name) { this.name = name; }
}
