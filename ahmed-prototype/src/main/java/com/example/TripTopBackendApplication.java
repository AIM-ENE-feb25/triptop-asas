package com.example;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TripTopBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TripTopBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            // Wacht even zodat de server gestart is (bij een echte applicatie beter een health-check)
            Thread.sleep(5000);

            String jsonPayload = "{\"username\":\"edevries\", \"password\":\"3g2Rw9sT1x\"}";

            // Aanroep naar endpoint voor v1
            HttpResponse<JsonNode> responseV1 = Unirest.post("http://localhost:8080/api/login/v1")
                    .header("Content-Type", "application/json")
                    .body(jsonPayload)
                    .asJson();
            System.out.println("Response v1: " + responseV1.getBody());

            // Aanroep naar endpoint voor v2
            HttpResponse<JsonNode> responseV2 = Unirest.post("http://localhost:8080/api/login/v2")
                    .header("Content-Type", "application/json")
                    .body(jsonPayload)
                    .asJson();
            System.out.println("Response v2: " + responseV2.getBody());
        };
    }
}
