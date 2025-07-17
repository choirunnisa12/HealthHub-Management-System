package com.example.healthhub.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "healthhub")
public class HealthHub {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "healthHub")
    private List<Doctor> doctors;

    @OneToMany(mappedBy = "healthHub")
    private List<Nurse> nurses;

    @OneToMany(mappedBy = "healthHub")
    private List<Patient> patients;

    // getter & setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public List<Doctor> getDoctors() { return doctors; }
    public void setDoctors(List<Doctor> doctors) { this.doctors = doctors; }
    public List<Nurse> getNurses() { return nurses; }
    public void setNurses(List<Nurse> nurses) { this.nurses = nurses; }
    public List<Patient> getPatients() { return patients; }
    public void setPatients(List<Patient> patients) { this.patients = patients; }
} 