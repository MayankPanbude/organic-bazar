package com.organicbazar.order.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.organicbazar.order.dto.ProductResponseDto;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/created-by/{email}")
    List<ProductResponseDto> getProductsByCreatedBy(@PathVariable("email") String email);
}

