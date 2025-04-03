package org.example.prototypepaymentsystem.service;

import org.example.prototypepaymentsystem.adapter.PaypalAdapter;
import org.example.prototypepaymentsystem.adapter.StripeAdapter;
import org.example.prototypepaymentsystem.dto.PaymentDTO;
import org.example.prototypepaymentsystem.repository.PaymentRepositoryImpl;
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
    @Autowired
    private PaymentRepositoryImpl paymentRepositoryImpl;

    public void processPayment(PaymentDTO paymentDTO) {
        String result;

        switch (paymentMethod.toLowerCase()) {
            case "stripe":
                result = stripeAdapter.createPayment(paymentDTO);
                break;
            case "paypal":
                result = paypalAdapter.createPayment(paymentDTO);
                break;
            default:
                throw new IllegalArgumentException("Unsupported payment method: " + paymentMethod);
        }

        // Opslaan in database
        paymentRepositoryImpl.savePayment(result, paymentDTO);
    }


    public void confirmPayment(String paymentId) {
        Boolean result;
        switch (paymentMethod.toLowerCase()) {
            case "stripe":
                result = stripeAdapter.confirmPayment(paymentId);
                break;
            case "paypal":
                result = paypalAdapter.confirmPayment(paymentId);
                break;
            default:
                throw new IllegalArgumentException("Unsupported payment method: " + paymentMethod);
        }
        paymentRepositoryImpl.markPaymentAsPaid(paymentId);
    }
}
