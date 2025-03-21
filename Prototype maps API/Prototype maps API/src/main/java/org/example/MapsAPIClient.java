package org.example;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class MapsAPIClient {

    private static final String API_URL = "https://maps-data.p.rapidapi.com/searchmaps.php";
    private static final String API_HOST = "maps-data.p.rapidapi.com";
    private static final String API_KEY = "7909c32a09msha2bffb6a5afe086p1a8f90jsn05260010df3c";

    public static void main(String[] args) {
        try {
            getMapData();
        } catch (IOException e) {
            System.err.println("Error while making the API request: " + e.getMessage());
        }
    }

    public static void getMapData() throws IOException {
        // URL met parameters
        String url = String.format("%s?query=restaurant&limit=20&country=us&lang=en&lat=51.5072&lng=0.12&offset=0&zoom=13", API_URL);

        // Maak een HTTP GET-verzoek
        HttpGet request = new HttpGet(url);
        request.setHeader("x-rapidapi-host", API_HOST);
        request.setHeader("x-rapidapi-key", API_KEY);

        // Maak een HttpClient aan en voer de aanvraag uit
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpResponse response = httpClient.execute(request);
            String responseBody = EntityUtils.toString(response.getEntity());

            // Print de response body
            System.out.println("Response: " + responseBody);
        }
    }
}
