package com.example.healthhub.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Entity
@Table(name = "nurses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Nurse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String department;

    @ManyToOne
    @JoinColumn(name = "healthhub_id")
    private HealthHub healthHub;

    @OneToMany(mappedBy = "nurse", cascade = CascadeType.ALL)
    private List<Patient> patients;
}