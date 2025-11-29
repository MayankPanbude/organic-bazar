package com.organicbazar.payment.client;

import com.organicbazar.payment.dto.ProductStockRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductClient {

    @PostMapping("/deduct-stock")
    void deductStock(@RequestBody List<ProductStockRequest> requests);
}
