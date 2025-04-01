package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginRepoImpl implements LoginRepo {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LoginRepoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Reiziger findByUsername(String username) {
        try {
            String sql = "SELECT username, password FROM reiziger WHERE username = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{username}, (rs, rowNum) ->
                    new Reiziger(rs.getString("username"), rs.getString("password"))
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void save(Reiziger reiziger) {
        String sql = "INSERT INTO reiziger (username, password) VALUES (?, ?)";
        jdbcTemplate.update(sql, reiziger.getUsername(), reiziger.getPassword());
    }
}
