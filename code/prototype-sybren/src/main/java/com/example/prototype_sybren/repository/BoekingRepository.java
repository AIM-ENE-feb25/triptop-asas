package com.example.prototype_sybren.repository;

import com.example.prototype_sybren.domain.Boeking;
import org.intellij.lang.annotations.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class BoekingRepository {
    private final JdbcTemplate database;

    @Autowired
    public BoekingRepository(JdbcTemplate jdbcTemplate) {
        this.database = jdbcTemplate;
    }

    public String slaBoekingOp(Boeking boeking) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        int interneBoeking = 0;
        String bericht = "";

        System.out.println(boeking.getOvernachtingId());


        if(boeking.getOvernachtingId() == 0 ) {
            interneBoeking = 1;
            bericht = "Boeking is geslaagd met Booking.com";
        } else {
            bericht = "Boeking is geslaagd via ons interne systeem";
        }

        @Language("TSQL")
        final String sql = """
            INSERT INTO  dbo.Boeking (boeking_datum, interne_boeking, hotel_naam, begin_datum, eind_datum)
            VALUES (?, ?, ?, ?, ?)
        """;

        this.database.update(sql, currentDateTime, interneBoeking, boeking.getHotelNaam(), boeking.getBeginDatum(), boeking.getEindDatum());

        return bericht;

    }
}
