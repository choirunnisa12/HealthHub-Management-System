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

    @ManyToOne
    @JoinColumn(name = "patient_id") // Menyimpan referensi ke pasien
    @NotNull(message = "Patient cannot be null")
    private Patient patient; // Mengubah dari String ke Patient

    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod; // Menggunakan enum yang didefinisikan di sini

    private LocalDateTime billingDate;

    // Enum untuk metode pembayaran
    public enum PaymentMethod {
        MANDIRI,
        ASURANSI
    }

    // Enum untuk status pembayaran
    public enum PaymentStatus {
        PAID,
        UNPAID
    }

    @ManyToOne
    @JoinColumn(name = "puskesmas_id")
    private Puskesmas puskesmas;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
