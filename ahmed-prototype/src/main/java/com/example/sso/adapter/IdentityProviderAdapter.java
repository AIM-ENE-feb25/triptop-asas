package com.example.sso.adapter;

import com.example.sso.dto.LoginResponse;

public interface IdentityProviderAdapter {
    LoginResponse login(String username, String password);
}
