package com.example.triptop.auth;

import com.example.triptop.dto.CheckResponse;

public interface AuthAdapter {
    String login(String username, String password);
    CheckResponse check(String token, String username, String application);
}
