package com.example.puskesmas.dto.request;

import com.example.puskesmas.entity.Billing;
import com.example.puskesmas.entity.Patient;
import com.example.puskesmas.entity.Puskesmas;
import com.example.puskesmas.entity.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BillingDTO {
    @NotNull
    private int patient_id;

    @NotNull
    private BigDecimal totalAmount;

    @NotNull
    private PaymentStatus paymentStatus;

    @NotNull
    private PaymentMethod paymentMethod;

    private LocalDateTime billingDate;

    @NotNull
    private int puskesmas_id;

    @NotNull
    private int user_id;

    // Enum for payment methods
    public enum PaymentMethod {
        MANDIRI,
        ASURANSI
    }

    // Enum for payment status
    public enum PaymentStatus {
        PAID,
        UNPAID
    }
}
