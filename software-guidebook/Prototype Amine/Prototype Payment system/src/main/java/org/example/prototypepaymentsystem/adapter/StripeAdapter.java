package org.example.prototypepaymentsystem.adapter;

import org.example.prototypepaymentsystem.dto.PaymentDTO;
import org.springframework.stereotype.Component;

@Component
public class StripeAdapter implements PaymentPort {
    @Override
    public void createPayment(PaymentDTO paymentDTO) {

    }

    @Override
    public void confirmPayment(String paymentId) {

    }


}
