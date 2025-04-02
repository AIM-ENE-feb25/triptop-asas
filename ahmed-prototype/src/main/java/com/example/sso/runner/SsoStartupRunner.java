package com.example.sso.runner;

import com.example.sso.service.SsoLoginService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SsoStartupRunner implements ApplicationRunner {

    private final SsoLoginService loginService;

    public SsoStartupRunner(SsoLoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        loginService.loginAllUsers();
    }
}
