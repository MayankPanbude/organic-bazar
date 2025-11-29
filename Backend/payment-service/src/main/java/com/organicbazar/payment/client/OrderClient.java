package com.organicbazar.payment.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.organicbazar.payment.dto.OrderItemDto;

@FeignClient(name = "order-service")
public interface OrderClient {

    @GetMapping("/{orderId}/items")
    List<OrderItemDto> getOrderItems(@PathVariable Long orderId);

    @PutMapping("/{orderId}/status")
    void updateOrderStatus(@PathVariable("orderId") Long orderId, @RequestParam("status") String status);
}

