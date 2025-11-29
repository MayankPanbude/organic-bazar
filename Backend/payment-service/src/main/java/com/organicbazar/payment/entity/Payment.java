package com.organicbazar.payment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;
    private Long customerId;
    private Double amount;
    private String method; // CARD, UPI, COD
    private String status; // SUCCESS, FAILED, PENDING
    private LocalDateTime paymentTime;
}
