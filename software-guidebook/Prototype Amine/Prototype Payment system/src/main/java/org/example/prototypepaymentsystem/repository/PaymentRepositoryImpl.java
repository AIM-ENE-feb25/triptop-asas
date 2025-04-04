package org.example.prototypepaymentsystem.repository;

import org.example.prototypepaymentsystem.dto.PaymentDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {

    private final JdbcTemplate jdbcTemplate;

    public PaymentRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void savePayment(String paymentId, PaymentDTO paymentDTO) {
        String sql = "INSERT INTO payments (payment_id, is_payd, currency_code, amount) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, paymentId, false, paymentDTO.getCurrencyCode(), paymentDTO.getAmount());
    }

    @Override
    public void markPaymentAsPaid(String paymentId) {
        String sql = "UPDATE payments SET is_payd = 1 WHERE payment_id = ?";
        jdbcTemplate.update(sql, paymentId);
    }
}
