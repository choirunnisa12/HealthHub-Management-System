package com.example.puskesmas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "billings")
@NoArgsConstructor
@AllArgsConstructor
public class Billing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "patient")
    @NotNull(message = "Patient cannot be null")
    private String patient;
    private BigDecimal totalAmount;
    public enum paymentMethod {
        MANDIRI,
        ASURANSI
    }
    public enum paymentStatus{
        PAID,UNPAID
    }
    private LocalDateTime billingDate;
}
