package com.organicbazar.product.dto;

import lombok.Data;

@Data
public class ProductStockRequest {
    private Long productId;
    private int quantity;
}
