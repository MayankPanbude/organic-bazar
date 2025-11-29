package com.organicbazar.cart.dto;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double price;
    private int stock;

    private Long categoryId;
    private String categoryName;

    // Stores the owner's username (farmer who created this product)
    private String createdBy;
}
