package com.organicbazar.payment.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.organicbazar.common.dto.CartItemDto;
import com.organicbazar.payment.client.OrderClient;
import com.organicbazar.payment.client.ProductClient;
import com.organicbazar.payment.dto.OrderItemDto;
import com.organicbazar.payment.dto.PaymentRequest;
import com.organicbazar.payment.dto.ProductStockRequest;
import com.organicbazar.payment.entity.Payment;
import com.organicbazar.payment.exception.PaymentNotFoundException;
import com.organicbazar.payment.repository.PaymentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
	private final PaymentRepository paymentRepository;

    private final ProductClient productClient;
    private final OrderClient orderClient;

    @Override
    public List<Payment> getPaymentsByCustomer(Long customerId) {
        return paymentRepository.findByCustomerId(customerId);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with ID " + id));
    }
    
    @Override
    @Transactional
    public Payment pay(Long orderId, PaymentRequest request) {
        // 1. Save payment
        Payment payment = new Payment();
        payment.setOrderId(orderId);
        payment.setCustomerId(request.getCustomerId());
        payment.setAmount(request.getAmount());
        payment.setMethod(request.getMethod());
        payment.setStatus("SUCCESS");
        payment.setPaymentTime(LocalDateTime.now());

        Payment savedPayment = paymentRepository.save(payment);

        // 2. Update order status to COMPLETED
        orderClient.updateOrderStatus(orderId, "COMPLETED");

        // 3. Deduct stock for each item
        List<OrderItemDto> items = orderClient.getOrderItems(orderId);
        List<ProductStockRequest> stockRequests = items.stream()
            .map(item -> new ProductStockRequest(item.getProductId(), item.getQuantity()))
            .collect(Collectors.toList());
        productClient.deductStock(stockRequests);

        return savedPayment;
    }


}
