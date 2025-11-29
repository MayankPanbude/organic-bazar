package com.organicbazar.payment.service;

import com.organicbazar.payment.dto.PaymentRequest;
import com.organicbazar.payment.entity.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> getPaymentsByCustomer(Long customerId);
    Payment getPaymentById(Long id);
    Payment pay(Long orderId, PaymentRequest request);
}
