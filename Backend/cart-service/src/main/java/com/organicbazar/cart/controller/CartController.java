package com.organicbazar.cart.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.organicbazar.cart.client.OrderServiceClient;
import com.organicbazar.cart.client.ProductServiceClient;
import com.organicbazar.cart.dto.CartItemDto;
import com.organicbazar.cart.dto.CartItemResponseDto;
import com.organicbazar.cart.dto.OrderItemDto;
import com.organicbazar.cart.dto.OrderRequest;
import com.organicbazar.cart.dto.OrderResponseDto;
import com.organicbazar.cart.dto.ProductResponseDto;
import com.organicbazar.cart.entity.CartItem;
import com.organicbazar.cart.security.JwtTokenProvider;
import com.organicbazar.cart.service.CartService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final JwtTokenProvider jwtTokenProvider;
    private final OrderServiceClient orderServiceClient;
    @Autowired
    private ProductServiceClient productServiceClient;

    @PostMapping
    public ResponseEntity<CartItem> add(@RequestBody CartItemDto dto, HttpServletRequest request) {
        Long customerId = jwtTokenProvider.getUserIdFromRequest(request);
        return ResponseEntity.ok(cartService.addToCart(dto, customerId));
    }

    @GetMapping
    public ResponseEntity<List<CartItemResponseDto>> getByCustomer(HttpServletRequest request) {
        Long customerId = jwtTokenProvider.getUserIdFromRequest(request);
        return ResponseEntity.ok(cartService.getCartItemsByCustomerId(customerId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cartService.removeItem(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/place-order")
    public ResponseEntity<OrderResponseDto> placeOrder(HttpServletRequest request) {
        Long customerId = jwtTokenProvider.getUserIdFromRequest(request);

        List<CartItem> cartItems = cartService.getCartItemsRaw(customerId);
        if (cartItems.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        double totalAmount = 0.0;
        List<OrderItemDto> orderItems = new ArrayList<>();

        for (CartItem item : cartItems) {
            ProductResponseDto product = productServiceClient.getProductById(item.getProductId());
            totalAmount += product.getPrice() * item.getQuantity();

            OrderItemDto orderItem = new OrderItemDto();
            orderItem.setProductId(product.getId());
            orderItem.setQuantity(item.getQuantity());
            orderItems.add(orderItem);
        }

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setCustomerId(customerId);
        orderRequest.setItems(orderItems);
        orderRequest.setTotalAmount(totalAmount);

        OrderResponseDto createdOrder = orderServiceClient.placeOrder(orderRequest);
        cartService.clearCart(customerId);

        return ResponseEntity.ok(createdOrder);
    }
}
