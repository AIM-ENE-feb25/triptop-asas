package com.example.prototype_sybren.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BoekingRepository {
    private final JdbcTemplate database;

    @Autowired
    public BoekingRepository(JdbcTemplate jdbcTemplate) {
        this.database = jdbcTemplate;
    }

    public String haalOvernachtingenOp() {
        return "Alle interne overnachtingen + ";
    }
}
