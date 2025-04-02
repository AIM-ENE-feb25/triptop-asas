package org.example.prototypepaymentsystem.service;

import org.example.prototypepaymentsystem.adapter.PaypalAdapter;
import org.example.prototypepaymentsystem.adapter.StripeAdapter;
import org.example.prototypepaymentsystem.dto.PaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Value("${payment.method}")
    private String paymentMethod;

    @Autowired
    private PaypalAdapter paypalAdapter;

    @Autowired
    private StripeAdapter stripeAdapter;

    public void processPayment(PaymentDTO payment) {
        switch (paymentMethod.toLowerCase()) {
            case "stripe":
                stripeAdapter.createPayment(payment);
                break;
            case "paypal":
                paypalAdapter.createPayment(payment);
                break;
            default:
                throw new IllegalArgumentException("Unsupported payment method: " + paymentMethod);
        }
    }

    public void confirmPayment(String paymentId) {
        switch (paymentMethod.toLowerCase()) {
            case "stripe":
                stripeAdapter.confirmPayment(paymentId);
                break;
            case "paypal":
                paypalAdapter.confirmPayment(paymentId);
                break;
            default:
                throw new IllegalArgumentException("Unsupported payment method: " + paymentMethod);
        }
    }
}
