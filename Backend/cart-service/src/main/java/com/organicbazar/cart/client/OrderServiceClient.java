package com.organicbazar.cart.client;

import com.organicbazar.cart.dto.OrderRequest;
import com.organicbazar.cart.dto.OrderResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "order-service")
public interface OrderServiceClient {

    @PostMapping("/")
    OrderResponseDto placeOrder(@RequestBody OrderRequest orderRequest);
}
