package com.example.prototype_sybren.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.Unirest;
import kong.unirest.HttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class BookingComAdapter {
    @Value("${app.api.key}")
    private String apiKey;

    @Value("${app.api.host}")
    private String apiHost;

    private static ObjectMapper objectMapper = new ObjectMapper();

    public String haalOvernachtingenOp() {
        String output = null;
        try {

            String stad = "Amsterdam";

            Map<String, String> loginPayload = new HashMap<>();
            loginPayload.put("searchDestination", stad);
            String payload = objectMapper.writeValueAsString(loginPayload);

            HttpResponse<String> response = Unirest.get("https://booking-com15.p.rapidapi.com/api/v1/hotels/searchDestination?query={destination}")
                    .header("Content-Type", "application/json")
                    .header("x-rapidapi-key", apiKey)
                    .header("x-rapidapi-host", apiHost)
                    .routeParam("destination", stad)
                    .asString();

            output = response.getBody();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Unirest.shutDown();
        }
        return output;
    }

}
