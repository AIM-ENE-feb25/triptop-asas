package com.example.sso.service;

import com.example.sso.adapter.IdentityProviderAdapter;
import com.example.sso.domain.SsoToken;
import com.example.sso.domain.SsoUser;
import com.example.sso.dto.LoginResponse;
import com.example.sso.repository.SsoTokenRepository;
import com.example.sso.repository.SsoUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SsoLoginService {

    private final SsoUserRepository userRepository;
    private final SsoTokenRepository tokenRepository;
    private final IdentityProviderAdapter identityProviderAdapter;

    public SsoLoginService(SsoUserRepository userRepository,
                           SsoTokenRepository tokenRepository,
                           IdentityProviderAdapter identityProviderAdapter) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.identityProviderAdapter = identityProviderAdapter;
    }

    public void loginAllUsers() {
        List<SsoUser> users = userRepository.findAll();
        for (SsoUser user : users) {
            LoginResponse response = identityProviderAdapter.login(user.getUsername(), user.getWachtwoord());
            SsoToken token = new SsoToken();
            token.setUsername(user.getUsername());
            token.setAccessToken(response.getToken());
            tokenRepository.save(token);
        }
    }
}
