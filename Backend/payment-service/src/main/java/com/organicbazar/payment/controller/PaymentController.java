package com.organicbazar.payment.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.organicbazar.payment.dto.PaymentRequest;
import com.organicbazar.payment.dto.PaymentResponse;
import com.organicbazar.payment.entity.Payment;
import com.organicbazar.payment.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class PaymentController {

	private final PaymentService paymentService;

    
	@PostMapping("/{orderId}")
	public ResponseEntity<PaymentResponse> pay(@PathVariable Long orderId, @RequestBody PaymentRequest request) {

	    Payment payment = paymentService.pay(orderId, request);

	    PaymentResponse response = new PaymentResponse(
	        payment.getId(),
	        payment.getOrderId(),
	        payment.getCustomerId(),
	        payment.getAmount(),
	        payment.getStatus()
	    );

	    return new ResponseEntity<>(response, HttpStatus.CREATED);
	}





    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Payment>> getByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(paymentService.getPaymentsByCustomer(customerId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }
}
