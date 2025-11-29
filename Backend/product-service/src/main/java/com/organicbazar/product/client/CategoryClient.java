package com.organicbazar.product.client;

import com.organicbazar.product.dto.CategoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Make sure this name matches category-service's spring.application.name
@FeignClient(name = "category-service")
public interface CategoryClient {

    @GetMapping("/{id}")
    CategoryDto getCategoryById(@PathVariable("id") Long id);
}
