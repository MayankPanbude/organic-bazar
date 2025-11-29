package com.organicbazar.order.dto;

import com.organicbazar.common.dto.CartItemDto;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private Long customerId;
    private Double totalAmount;
    private List<CartItemDto> items;
}
