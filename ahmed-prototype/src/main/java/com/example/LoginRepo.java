package com.example;

public interface LoginRepo {
    Reiziger findByUsername(String username);
    void save(Reiziger reiziger);
}
