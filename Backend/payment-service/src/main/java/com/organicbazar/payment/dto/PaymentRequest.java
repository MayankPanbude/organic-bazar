package com.organicbazar.payment.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private Long orderId;
    private Long customerId;
    private Double amount;
    private String method;
}
