package com.example.triptop.auth;

public interface AuthenticationStrategy {
    String login(String username, String password);
    boolean check(String token, String username, String application);
}
