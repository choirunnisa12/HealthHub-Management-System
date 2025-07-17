package com.example.healthhub.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "medicines")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private LocalDate expirationDate;

    @ManyToOne
    @JoinColumn(name = "healthhub_id")
    private HealthHub healthhub;

    @ManyToMany(mappedBy = "medicines")
    private List<Patient> patients;

    public String getDosage() { return type; }

    public void setDosage(String type) { this.type = type; }
}