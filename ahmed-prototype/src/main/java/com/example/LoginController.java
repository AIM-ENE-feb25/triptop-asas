package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    // Endpoint for v1 (old version, token with extra "haar")
    @PostMapping("/v1")
    public AuthToken authenticateV1(@RequestBody Reiziger reiziger) throws AuthenticationException {
        return loginService.authenticateV1(reiziger);
    }

    // Endpoint for v2 (new version, token as provided)
    @PostMapping("/v2")
    public AuthToken authenticateV2(@RequestBody Reiziger reiziger) throws AuthenticationException {
        return loginService.authenticateV2(reiziger);
    }
}
