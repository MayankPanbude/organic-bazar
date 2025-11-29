package com.organicbazar.cart.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.organicbazar.cart.client.ProductServiceClient;
import com.organicbazar.cart.dto.CartItemDto;
import com.organicbazar.cart.dto.CartItemResponseDto;
import com.organicbazar.cart.dto.ProductResponseDto;
import com.organicbazar.cart.entity.CartItem;
import com.organicbazar.cart.exception.CartItemNotFoundException;
import com.organicbazar.cart.repository.CartItemRepository;
import com.organicbazar.cart.repository.CartRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductServiceClient productServiceClient;

    @Override
    public CartItem addToCart(CartItemDto dto, Long customerId) {
        CartItem item = CartItem.builder()
                .productId(dto.getProductId())
                .customerId(customerId)
                .quantity(dto.getQuantity())
                .build();
        return cartRepository.save(item);
    }

    @Override
    public List<CartItemResponseDto> getCartItemsByCustomerId(Long customerId) {
        List<CartItem> cartItems = cartRepository.findByCustomerId(customerId);

        return cartItems.stream().map(cartItem -> {
            CartItemResponseDto response = new CartItemResponseDto();
            response.setId(cartItem.getId());
            response.setProductId(cartItem.getProductId());
            response.setQuantity(cartItem.getQuantity());

            // Fetch product using Feign
            ProductResponseDto product = productServiceClient.getProductById(cartItem.getProductId());
            response.setProduct(product);

            return response;
        }).toList();
    }


    @Override
    public void removeItem(Long id) {
        if (!cartRepository.existsById(id)) {
            throw new CartItemNotFoundException("Cart item not found");
        }
        cartRepository.deleteById(id);
    }

    @Override
    public List<CartItem> getCartItemsRaw(Long customerId) {
        return cartItemRepository.findByCustomerId(customerId);
    }


    @Override
    public void clearCart(Long customerId) {
        List<CartItem> cartItems = cartItemRepository.findByCustomerId(customerId);
        cartItemRepository.deleteAll(cartItems);
    }
}
