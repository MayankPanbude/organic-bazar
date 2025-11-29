package com.organicbazar.payment.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderDto {
    private Long id;
    private Long customerId;
    private Double totalAmount;
    private String status;
    private List<OrderItemDto> items;
}
