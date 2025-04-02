package org.example.prototypepaymentsystem.controller;

import org.example.prototypepaymentsystem.dto.PaymentDTO;
import org.example.prototypepaymentsystem.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @PostMapping("/createPayment")
    public ResponseEntity<String> createPayment(@RequestBody Map<String, Object> request) {
        double amount = Double.parseDouble(request.get("amount").toString());
        String currencyCode = request.get("currencyCode").toString();

        if (amount <= 0) {
            return ResponseEntity.badRequest().body("Amount must be greater than zero");
        }
        paymentService.processPayment(new PaymentDTO(currencyCode, amount));
        return ResponseEntity.ok("Payment of " + amount + " " + currencyCode + " created successfully.");
    }
}
