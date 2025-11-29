package com.organicbazar.product.dto;

import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private String description;
    private double price;
    private int stock;
    private Long categoryId;
}
