package com.example.triptop.controller;

import com.example.triptop.auth.AuthenticationStrategy;
import com.example.triptop.model.User;
import com.example.triptop.dto.CheckRequest;
import com.example.triptop.dto.LoginRequest;
import com.example.triptop.dto.LoginResponse;
import com.example.triptop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    @Qualifier("v1")
    private AuthenticationStrategy authStrategy;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String token = authStrategy.login(loginRequest.getUsername(), loginRequest.getPassword());

        Optional<User> userOptional = userRepository.findByUsername(loginRequest.getUsername());
        User user = userOptional.orElse(new User(loginRequest.getUsername()));
        user.setToken(token);
        userRepository.save(user);

        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/check")
    public ResponseEntity<?> check(@RequestParam String token,
                                   @RequestBody CheckRequest checkRequest) {
        boolean access = authStrategy.check(token, checkRequest.getUsername(), checkRequest.getApplication());
        return ResponseEntity.ok(Collections.singletonMap("access", access));
    }
}
