package com.organicbazar.product.service;

import java.util.List;

import com.organicbazar.product.dto.ProductDto;
import com.organicbazar.product.dto.ProductResponseDto;
import com.organicbazar.product.dto.ProductStockRequest;

public interface ProductService {
    ProductResponseDto createProduct(ProductDto dto);

    List<ProductResponseDto> getAllProducts();

    ProductResponseDto getProductById(Long id);

    void deleteProduct(Long id);

    List<ProductResponseDto> getMyProducts();

    ProductResponseDto updateProduct(Long id, ProductDto dto);
    
    void deductStock(List<ProductStockRequest> requests);
    
    List<ProductResponseDto> getProductsByCreatedBy(String email);

    
    
}
