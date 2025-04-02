package org.example.prototypepaymentsystem.adapter;

import org.example.prototypepaymentsystem.dto.PaymentDTO;

public interface PaymentPort {
    void createPayment(PaymentDTO paymentDTO);
    void confirmPayment(String paymentId);
}
