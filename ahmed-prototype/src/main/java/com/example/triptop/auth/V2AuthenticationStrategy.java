package com.example.triptop.auth;

import kong.unirest.Unirest;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component("v2")
public class V2AuthenticationStrategy implements AuthenticationStrategy {

    private static final String LOGIN_URL = "http://api.example.com/v2/login";
    private static final String CHECK_URL = "http://api.example.com/v2/check";

    @Override
    public String login(String username, String password) {
        try {
            JSONObject body = new JSONObject();
            body.put("username", username);
            body.put("password", password);

            HttpResponse<JsonNode> response = Unirest.post(LOGIN_URL)
                    .header("Content-Type", "application/json")
                    .body(body.toString())
                    .asJson();

            kong.unirest.json.JSONObject responseObject = response.getBody().getObject();
            return responseObject.optString("token", null);
        } catch (Exception e) {
            // Log exception as needed
            return null;
        }
    }

    @Override
    public boolean check(String token, String username, String application) {
        try {
            JSONObject body = new JSONObject();
            body.put("username", username);
            body.put("application", application);

            // Token passed as an HTTP header in v2
            HttpResponse<JsonNode> response = Unirest.post(CHECK_URL)
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .body(body.toString())
                    .asJson();

            kong.unirest.json.JSONObject responseObject = response.getBody().getObject();
            return responseObject.optBoolean("access", false);
        } catch (Exception e) {
            // Log exception as needed
            return false;
        }
    }
}
