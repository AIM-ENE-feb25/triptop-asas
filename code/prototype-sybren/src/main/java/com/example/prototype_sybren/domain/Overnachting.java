package com.example.prototype_sybren.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Overnachting {
    private String bestemming;
    private Date beginDatum;
    private Date eindDatum;
}
