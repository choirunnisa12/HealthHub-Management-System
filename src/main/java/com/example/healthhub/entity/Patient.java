package com.example.healthhub.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Entity
@Table(name = "patients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String phone;
    private String birthDate;
    private Integer age;
    private String assignedDoctor;
    private String admittedDate;
    private Boolean hasAllergies;
    private String symptoms;

    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public String getAssignedDoctor() { return assignedDoctor; }
    public void setAssignedDoctor(String assignedDoctor) { this.assignedDoctor = assignedDoctor; }
    public String getAdmittedDate() { return admittedDate; }
    public void setAdmittedDate(String admittedDate) { this.admittedDate = admittedDate; }
    public Boolean isHasAllergies() { return hasAllergies; }
    public void setHasAllergies(Boolean hasAllergies) { this.hasAllergies = hasAllergies; }
    public String getSymptoms() { return symptoms; }
    public void setSymptoms(String symptoms) { this.symptoms = symptoms; }

    @ManyToOne
    @JoinColumn(name = "healthhub_id")
    private HealthHub healthHub;

    public HealthHub getHealthHub() { return healthHub; }
    public void setHealthHub(HealthHub healthHub) { this.healthHub = healthHub; }

    @ManyToMany
    @JoinTable(
        name = "patient_doctor",
        joinColumns = @JoinColumn(name = "patient_id"),
        inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    private List<Doctor> doctors;

    @ManyToOne
    @JoinColumn(name = "nurse_id")
    private Nurse nurse;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<MedicalRecord> medicalRecords;

    @ManyToMany
    @JoinTable(
        name = "patient_medicine",
        joinColumns = @JoinColumn(name = "patient_id"),
        inverseJoinColumns = @JoinColumn(name = "medicine_id")
    )
    private List<Medicine> medicines;
}

