package com.example.triptop.auth;

import com.example.triptop.dto.CheckResponse;
import kong.unirest.Unirest;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component("wireMockAuthAdapterV1")
public class WireMockAuthAdapterV1 implements AuthAdapter {

    private static final String LOGIN_URL = "http://identity-wiremock.minordevops.nl:8080/login";
    private static final String CHECK_URL = "http://identity-wiremock.minordevops.nl:8080/checkAppAccess";

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
            System.out.println(responseObject);
            return responseObject.optString("token", null);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public CheckResponse check(String token, String username, String application) {
        try {
            JSONObject body = new JSONObject();
            body.put("username", username);
            body.put("application", application);

            String urlWithToken = CHECK_URL + "?token=" + token;
            HttpResponse<JsonNode> response = Unirest.post(urlWithToken)
                    .header("Content-Type", "application/json")
                    .body(body.toString())
                    .asJson();

            kong.unirest.json.JSONObject responseObject = response.getBody().getObject();
            return new CheckResponse(responseObject);
        } catch (Exception e) {
            return new CheckResponse("denied", "");
        }
    }
}
