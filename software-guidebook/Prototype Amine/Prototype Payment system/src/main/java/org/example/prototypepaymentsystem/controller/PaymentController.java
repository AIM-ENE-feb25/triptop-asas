package org.example.prototypepaymentsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @PostMapping("/createPayment")
    public ResponseEntity<String> createPayment(@RequestBody Map<String, Object> request) {
        double amount = Double.parseDouble(request.get("amount").toString());
        String currencyCode = request.get("currencyCode").toString();

        if (amount <= 0) {
            return ResponseEntity.badRequest().body("Amount must be greater than zero");
        }

        return ResponseEntity.ok("Payment of " + amount + " " + currencyCode + " created successfully.");
    }
}
