package com.example.triptop.auth;

import com.example.triptop.dto.CheckResponse;
import kong.unirest.Unirest;
import kong.unirest.HttpResponse;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component("wireMockAuthAdapterV2")
public class WireMockAuthAdapterV2 implements AuthAdapter {

    private static final String LOGIN_URL = "http://localhost:8080/login";
    private static final String CHECK_URL = "http://localhost:8080/checkAppAccess";

    @Override
    public String login(String username, String password) {
        try {
            JSONObject body = new JSONObject();
            body.put("username", username);
            body.put("password", password);

            HttpResponse<String> response = Unirest.post(LOGIN_URL)
                    .header("Content-Type", "application/json")
                    .body(body.toString())
                    .asString();

            String token = response.getHeaders().getFirst("token");
            return token;
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

            HttpResponse<String> response = Unirest.post(CHECK_URL)
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .body(body.toString())
                    .asString();

            String responseBody = response.getBody();
            System.out.println(responseBody);
            String access = extractTagValue(responseBody, "access");
            String role = extractTagValue(responseBody, "role");
            return new CheckResponse(access, role);
        } catch (Exception e) {
            return new CheckResponse("denied", "");
        }
    }

    private String extractTagValue(String xml, String tagName) {
        String startTag = "<" + tagName + ">";
        String endTag = "</" + tagName + ">";
        int startIndex = xml.indexOf(startTag);
        int endIndex = xml.indexOf(endTag);
        if (startIndex != -1 && endIndex != -1 && endIndex > startIndex) {
            return xml.substring(startIndex + startTag.length(), endIndex);
        }
        return "";
    }
}
