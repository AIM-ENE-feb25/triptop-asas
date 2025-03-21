package com.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.util.HashMap;
import java.util.Map;

public class WiremockClient {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        try {
            String username = "edevries";
            String password = "3g2Rw9sT1x";

            Map<String, String> loginPayload = new HashMap<>();
            loginPayload.put("username", username);
            loginPayload.put("password", password);
            String loginJson = objectMapper.writeValueAsString(loginPayload);

            HttpResponse<String> loginResponse = Unirest.post("https://triptop-identity.wiremockapi.cloud/login")
                    .header("Content-Type", "application/json")
                    .body(loginJson)
                    .asString();

            System.out.println("Login response: " + loginResponse.getBody());

            JsonNode loginResponseJson = objectMapper.readTree(loginResponse.getBody());
            JsonNode tokenNode = loginResponseJson.get("token");
            String token = tokenNode.get("value").asText();
            System.out.println("Gegenereerd token: " + token);

            String checkAppAccessUrl = "https://triptop-identity.wiremockapi.cloud/checkAppAccess?token=" + token;

            Map<String, String> accessPayload = new HashMap<>();
            accessPayload.put("username", username);
            accessPayload.put("application", "triptop");
            String accessJson = objectMapper.writeValueAsString(accessPayload);

            HttpResponse<String> accessResponse = Unirest.post(checkAppAccessUrl)
                    .header("Content-Type", "application/json")
                    .body(accessJson)
                    .asString();

            System.out.println("CheckAppAccess response: " + accessResponse.getBody());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Unirest.shutDown();
        }
    }
}
