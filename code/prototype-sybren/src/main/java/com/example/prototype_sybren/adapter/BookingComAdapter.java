package com.example.prototype_sybren.adapter;

import com.example.prototype_sybren.domain.Boeking;
import com.example.prototype_sybren.domain.Overnachting;
import com.example.prototype_sybren.dto.OvernachtingResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.Unirest;
import kong.unirest.HttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class BookingComAdapter {
    @Value("${app.api.key}")
    private String apiKey;

    @Value("${app.api.host}")
    private String apiHost;

    private static ObjectMapper objectMapper = new ObjectMapper();

    public List<OvernachtingResponse> haalOvernachtingenOp(Overnachting overnachting) {
        ArrayList<Integer> hotels = haalBestemmingenOp(overnachting);
        return haalMogelijkhedenOp(hotels, overnachting);
    }

    public ArrayList<Integer> haalBestemmingenOp(Overnachting overnachting) {
        ArrayList<Integer> hotels = new ArrayList<>();
        try {

            Map<String, String> loginPayload = new HashMap<>();
            loginPayload.put("searchDestination", overnachting.getBestemming());
            String payload = objectMapper.writeValueAsString(loginPayload);

            HttpResponse<String> response = Unirest.get("https://booking-com15.p.rapidapi.com/api/v1/hotels/searchDestination?query={destination}")
                    .header("Content-Type", "application/json")
                    .header("x-rapidapi-key", apiKey)
                    .header("x-rapidapi-host", apiHost)
                    .routeParam("destination", overnachting.getBestemming())
                    .asString();

            String output = response.getBody();
            JsonNode rootNood = objectMapper.readTree(response.getBody());
            JsonNode dataArray = rootNood.path("data");
            for (JsonNode bestemming : dataArray) {
                int destId = bestemming.path("dest_id").asInt();
                hotels.add(destId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Unirest.shutDown();
        }
        return hotels;
    }

    public ArrayList<OvernachtingResponse> haalMogelijkhedenOp(ArrayList<Integer> hotels, Overnachting overnachting) {
        ArrayList<OvernachtingResponse> overnachtingResponses = new ArrayList<OvernachtingResponse>();

        for(int i = 0; i < hotels.size(); i++){
            try {

                Map<String, String> payload = new HashMap<>();
                payload.put("searchDestination", hotels.get(i).toString());

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date beginDatum = overnachting.getBeginDatum();
                String beginDatumString = sdf.format(beginDatum);
                Date eindDatum = overnachting.getEindDatum();
                String eindDatumString = sdf.format(eindDatum);

                HttpResponse<String> response = Unirest.get("https://booking-com15.p.rapidapi.com/api/v1/hotels/searchHotels?dest_id={bestemming}&search_type=CITY&arrival_date={beginDatum}&departure_date={eindDatum}&adults=1&children_age=0%2C17&room_qty=1&page_number=1&units=metric&temperature_unit=c&languagecode=en-us&currency_code=AED&location=US")
                        .header("Content-Type", "application/json")
                        .header("x-rapidapi-key", apiKey)
                        .header("x-rapidapi-host", apiHost)
                        .routeParam("bestemming", hotels.get(i).toString())
                        .routeParam("beginDatum", beginDatumString)
                        .routeParam("eindDatum", eindDatumString)
                        .asString();

                String output = response.getBody();
                JsonNode rootNood = objectMapper.readTree(output);
                JsonNode dataArray = rootNood.path("data").path("hotels");
                for (JsonNode bestemming : dataArray) {
                    String name = bestemming.path("accessibilityLabel").toString();
                    String shortenedName = name.split("\\.")[0];
                    OvernachtingResponse overnachtingResponse = new OvernachtingResponse();
                    String stad = bestemming.path("property").path("wishlistName").toString();
                    overnachtingResponse.setStad(stad);
                    overnachtingResponse.setHotelNaam(shortenedName);
                    overnachtingResponse.setBeginDatum(overnachting.getBeginDatum());
                    overnachtingResponse.setEindDatum(overnachting.getEindDatum());
                    overnachtingResponses.add(overnachtingResponse);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Unirest.shutDown();
            }
        }

        return overnachtingResponses;
    }

}
