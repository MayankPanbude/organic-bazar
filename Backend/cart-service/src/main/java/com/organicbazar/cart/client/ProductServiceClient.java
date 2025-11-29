package com.organicbazar.cart.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.organicbazar.cart.dto.ProductResponseDto;

@FeignClient(name = "product-service")
public interface ProductServiceClient {
    @GetMapping("/{id}")
    ProductResponseDto getProductById(@PathVariable Long id);
}
