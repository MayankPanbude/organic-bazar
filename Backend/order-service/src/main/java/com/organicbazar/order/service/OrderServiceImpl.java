package com.organicbazar.order.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.organicbazar.common.dto.CartItemDto;
import com.organicbazar.order.client.ProductClient;
import com.organicbazar.order.dto.OrderRequest;
import com.organicbazar.order.dto.ProductResponseDto;
import com.organicbazar.order.entity.Order;
import com.organicbazar.order.entity.OrderItem;
import com.organicbazar.order.exception.OrderNotFoundException;
import com.organicbazar.order.repository.OrderItemRepository;
import com.organicbazar.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductClient productClient;


    @Override
    public Order placeOrder(OrderRequest request) {
        Order order = Order.builder()
                .customerId(request.getCustomerId())
                .totalAmount(request.getTotalAmount())
                .status("PENDING")
                .orderDate(LocalDateTime.now())
                .build();

        Order savedOrder = orderRepository.save(order);

        // Save order items
        List<OrderItem> items = request.getItems().stream()
                .map(dto -> OrderItem.builder()
                        .productId(dto.getProductId())
                        .quantity(dto.getQuantity())
                        .order(savedOrder)
                        .build())
                .toList();

        orderItemRepository.saveAll(items);

        return savedOrder;
    }


    @Override
    public List<Order> getOrdersByCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id " + id));
    }

    @Override
    public void cancelOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        order.setStatus("CANCELLED");
        orderRepository.save(order);
    }
    
    @Override
    public List<CartItemDto> getOrderItems(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        return order.getOrderItems().stream()
                .map(item -> new CartItemDto(
                        item.getProductId(),
                        item.getQuantity()
                )).toList();
    }

    @Override
    public void updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderId));
        order.setStatus(status);
        orderRepository.save(order);
    }
    
    @Override
    public List<Order> getOrdersByFarmerEmail(String email) {
        // 1. Get products created by farmer
        List<ProductResponseDto> products = productClient.getProductsByCreatedBy(email);
        List<Long> productIds = products.stream()
                                       .map(ProductResponseDto::getId)
                                       .collect(Collectors.toList());
        if (productIds.isEmpty()) {
            return List.of(); // no products, no orders
        }

        // 2. Find orderIds containing those productIds
        List<Long> orderIds = orderItemRepository.findOrderIdsByProductIds(productIds);
        if (orderIds.isEmpty()) {
            return List.of();
        }

        // 3. Fetch and return orders with those IDs
        return orderRepository.findByIdIn(orderIds);
    }


}
