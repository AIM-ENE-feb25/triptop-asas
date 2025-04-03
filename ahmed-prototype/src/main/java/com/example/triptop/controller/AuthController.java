package com.example.triptop.controller;

import com.example.triptop.dto.CheckRequest;
import com.example.triptop.dto.CheckResponse;
import com.example.triptop.dto.LoginRequest;
import com.example.triptop.dto.LoginResponse;
import com.example.triptop.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.login(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/check")
    public ResponseEntity<CheckResponse> check(@RequestParam String token,
                                               @RequestBody CheckRequest checkRequest) {
        CheckResponse response = authService.check(token, checkRequest);
        return ResponseEntity.ok(response);
    }
}
