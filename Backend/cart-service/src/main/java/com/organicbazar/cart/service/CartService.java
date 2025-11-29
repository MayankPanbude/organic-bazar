package com.organicbazar.cart.service;

import com.organicbazar.cart.dto.CartItemDto;
import com.organicbazar.cart.dto.CartItemResponseDto;
import com.organicbazar.cart.entity.CartItem;

import java.util.List;

public interface CartService {
    CartItem addToCart(CartItemDto dto, Long customerId);
    List<CartItemResponseDto> getCartItemsByCustomerId(Long customerId);
    void removeItem(Long id);
    List<CartItem> getCartItemsRaw(Long customerId);
    void clearCart(Long customerId);

}
