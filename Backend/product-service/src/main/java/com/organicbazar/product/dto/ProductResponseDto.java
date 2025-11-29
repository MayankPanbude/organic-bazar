package com.organicbazar.product.dto;

import lombok.Data;

@Data
public class ProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private Long categoryId;
    private String categoryName;
    private String createdBy; // display owner in responses
}
