package org.example.prototypepaymentsystem.adapter;

import org.example.prototypepaymentsystem.dto.PaymentDTO;

public interface PaymentPort {
    String createPayment(PaymentDTO paymentDTO);
    Boolean confirmPayment(String paymentId);
}
