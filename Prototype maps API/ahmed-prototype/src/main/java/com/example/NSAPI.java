package com.example;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class NSAPI {

    public static void main(String[] args) {
        String apiKey = System.getProperty("ns.api.key");
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalStateException("Zet de NS API-key via -Dns.api.key=YOUR_API_KEY");
        }

        String url = "https://gateway.apiportal.ns.nl/reisinformatie-api/api/v2/arrivals";

        String stationCode = "MGZB";
        String lang = "nl";
        int maxJourneys = 40;

        String dateTime = ZonedDateTime.now().plusHours(12)
                .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        System.out.println("Verzenden verzoek naar: " + url);
        System.out.println("Met parameters: station=" + stationCode + ", lang=" + lang
                + ", dateTime=" + dateTime + ", maxJourneys=" + maxJourneys);

        try {
            HttpResponse<String> response = Unirest.get(url)
                    .header("Ocp-Apim-Subscription-Key", apiKey)
                    .queryString("station", stationCode)
                    .queryString("lang", lang)
                    .queryString("dateTime", dateTime)
                    .queryString("maxJourneys", maxJourneys)
                    .asString();

            System.out.println("HTTP-statuscode: " + response.getStatus());
            if(response.getStatus() == 200) {
                System.out.println("API-response succesvol ontvangen.");
                System.out.println("Response body:");
                System.out.println(response.getBody());
            } else {
                System.out.println("Fout bij het ophalen van aankomsten:");
                System.out.println(response.getBody());
            }
        } catch (UnirestException e) {
            System.err.println("Er is een fout opgetreden tijdens het uitvoeren van het verzoek:");
            e.printStackTrace();
        } finally {
            Unirest.shutDown();
        }
    }
}
