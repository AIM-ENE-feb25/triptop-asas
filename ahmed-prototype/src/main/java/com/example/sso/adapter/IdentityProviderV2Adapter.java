package com.example.sso.adapter;

import com.example.sso.dto.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

public class IdentityProviderV2Adapter implements IdentityProviderAdapter {

    private final RestTemplate restTemplate;
    private static final String LOGIN_URL = "https://triptop-identity.wiremockapi.cloud/login";

    public IdentityProviderV2Adapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public LoginResponse login(String username, String password) {
        Map<String, String> request = new HashMap<>();
        request.put("username", username);
        request.put("password", password);

        ResponseEntity<LoginResponse> responseEntity =
                restTemplate.postForEntity(LOGIN_URL, request, LoginResponse.class);

        LoginResponse response = responseEntity.getBody();

        String encryptedToken = encryptSha256(response.getToken());
        response.setToken(encryptedToken);

        return response;
    }

    private String encryptSha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
