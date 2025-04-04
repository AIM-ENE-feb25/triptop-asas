package org.example.prototypepaymentsystem.repository;
import org.example.prototypepaymentsystem.dto.PaymentDTO;

public interface PaymentRepository {
    void savePayment(String paymentId, PaymentDTO paymentDTO);
    void markPaymentAsPaid(String paymentId);
}
