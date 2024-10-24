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
    @Column(name = "phoneNumber")
    private String phoneNumber;

    @OneToMany(mappedBy = "puskesmas", cascade = CascadeType.ALL)
    private List<Doctor> doctorList;



}
