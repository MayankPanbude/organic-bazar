package com.organicbazar.order.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.organicbazar.common.dto.CartItemDto;
import com.organicbazar.order.dto.OrderRequest;
import com.organicbazar.order.entity.Order;
import com.organicbazar.order.security.JwtTokenProvider;
import com.organicbazar.order.service.OrderService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final JwtTokenProvider jwtTokenProvider;


    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequest request) {
        return ResponseEntity.ok(orderService.placeOrder(request));
    }

    @GetMapping("/customer")
    public ResponseEntity<List<Order>> getCustomerOrders(HttpServletRequest request) {
        Long customerId = jwtTokenProvider.getCustomerIdFromRequest(request); // implement this
        return ResponseEntity.ok(orderService.getOrdersByCustomer(customerId));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable Long id) {
        orderService.cancelOrder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{orderId}/items")
    public ResponseEntity<List<CartItemDto>> getOrderItems(@PathVariable Long orderId) {
        List<CartItemDto> items = orderService.getOrderItems(orderId);
        return ResponseEntity.ok(items);
    }
    
    @PutMapping("/{orderId}/status")
    public ResponseEntity<String> updateOrderStatus(@PathVariable Long orderId,@RequestParam("status") String status
    ) {
        orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok("Order status updated to " + status);
    }
    
    @GetMapping("/farmer/orders")
    public ResponseEntity<List<Order>> getOrdersByFarmerEmail(HttpServletRequest request) {
        String farmerEmail = jwtTokenProvider.getUsernameFromRequest(request); // or however you get email from JWT token
        List<Order> orders = orderService.getOrdersByFarmerEmail(farmerEmail);
        return ResponseEntity.ok(orders);
    }


}
