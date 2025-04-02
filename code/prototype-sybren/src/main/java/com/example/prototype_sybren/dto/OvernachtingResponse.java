package com.example.prototype_sybren.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OvernachtingResponse {
    private int overnachting_id;
    private String stad;
    private String hotelNaam;
    private Date beginDatum;
    private Date eindDatum;
}
