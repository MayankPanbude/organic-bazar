package com.organicbazar.order.service;

import java.util.List;

import com.organicbazar.common.dto.CartItemDto;
import com.organicbazar.order.dto.OrderRequest;
import com.organicbazar.order.entity.Order;

public interface OrderService {
    Order placeOrder(OrderRequest request);
    List<Order> getOrdersByCustomer(Long customerId);
    Order getOrderById(Long id);
    void cancelOrder(Long id);
    List<CartItemDto> getOrderItems(Long orderId);
    void updateOrderStatus(Long orderId, String status);
    List<Order> getOrdersByFarmerEmail(String email);
}
