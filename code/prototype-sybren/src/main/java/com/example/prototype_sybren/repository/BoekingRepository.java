package com.example.prototype_sybren.repository;

import com.example.prototype_sybren.service.BoekingOverzichtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BoekingRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BoekingRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
