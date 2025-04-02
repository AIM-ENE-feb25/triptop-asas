package org.example.prototypepaymentsystem.service;

import org.example.prototypepaymentsystem.dto.PaymentDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Value("${payment.method}")
    private String paymentMethod;

    public void processPayment(PaymentDTO payment) {
        switch (paymentMethod.toLowerCase()) {
            case "stripe":
                System.out.println("Processing payment with Stripe");
                break;
            case "paypal":
                System.out.println("Processing payment with PayPal");
                break;
            default:
                throw new IllegalArgumentException("Unsupported payment method: " + paymentMethod);
        }
    }
}
