package com.organicbazar.cart.dto;

import lombok.Data;

@Data
public class OrderResponseDto {
    private Long id;
    private Long customerId;
    private String status;
    private double totalAmount;
}
