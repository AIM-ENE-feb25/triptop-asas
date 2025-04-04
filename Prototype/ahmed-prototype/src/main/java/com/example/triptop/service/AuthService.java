package com.example.triptop.service;

import com.example.triptop.auth.AuthAdapter;
import com.example.triptop.dto.CheckRequest;
import com.example.triptop.dto.CheckResponse;
import com.example.triptop.dto.LoginRequest;
import com.example.triptop.dto.LoginResponse;
import com.example.triptop.model.User;
import com.example.triptop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    @Qualifier("wireMockAuthAdapterV2")
    private AuthAdapter authAdapter;

    @Autowired
    private UserRepository userRepository;

    public LoginResponse login(LoginRequest loginRequest) {
        String token = authAdapter.login(loginRequest.getUsername(), loginRequest.getPassword());
        Optional<User> userOptional = userRepository.findByUsername(loginRequest.getUsername());
        User user = userOptional.orElse(new User(loginRequest.getUsername()));
        user.setToken(token);
        userRepository.save(user);
        return new LoginResponse(token);
    }

    public CheckResponse check(String token, CheckRequest checkRequest) {
        return authAdapter.check(token, checkRequest.getUsername(), checkRequest.getApplication());
    }
}
