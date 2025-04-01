package com.example;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.stereotype.Component;

@Component("wireMockAdapterV1")
public class WireMockAdapterV1 implements LoginPort {

    @Override
    public AuthToken authenticateExternal(Reiziger reiziger) throws AuthenticationException {
        try {
            String requestBody = String.format("{\"username\": \"%s\", \"password\": \"%s\"}",
                    reiziger.getUsername(), reiziger.getPassword());

            HttpResponse<JsonNode> response = Unirest.post("http://identity-wiremock.minordevops.nl:8080/login")
                    .header("Content-Type", "application/json")
                    .body(requestBody)
                    .asJson();

            if (response.getStatus() == 200) {
                String token = response.getBody().getObject().getString("token");
                // Simulate v1 behavior: add extra "haar" to the token
                token = token + "haar";
                return new AuthToken(token);
            } else {
                throw new AuthenticationException("Authentication failed in WireMockAdapterV1");
            }
        } catch (Exception e) {
            throw new AuthenticationException("Error in WireMockAdapterV1: " + e.getMessage());
        }
    }
}
