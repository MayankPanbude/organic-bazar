package com.organicbazar.cart.dto;

import lombok.Data;

@Data
public class ProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private Long categoryId;
}
