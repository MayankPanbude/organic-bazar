package com.organicbazar.cart.dto;

import lombok.Data;

@Data
public class CartItemResponseDto {
    private Long id;
    private Long productId;
    private int quantity;
    private ProductResponseDto product;
}
