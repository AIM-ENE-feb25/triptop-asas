package com.example.prototype_sybren.domain;

import java.util.Date;

public class Boeking {
    private int boekindId;
    private int reizigerId;
    Date boekingDatum;
    Date vertrekDatum;
    Date aankomstDatum;
    String vertrekLocatie;
    String aanKomstLocatie;
    double prijs;

    public int getBoekindId() {
        return boekindId;
    }

    public void setBoekindId(int boekindId) {
        this.boekindId = boekindId;
    }

    public int getReizigerId() {
        return reizigerId;
    }

    public void setReizigerId(int reizigerId) {
        this.reizigerId = reizigerId;
    }

    public Date getBoekingDatum() {
        return boekingDatum;
    }

    public void setBoekingDatum(Date boekingDatum) {
        this.boekingDatum = boekingDatum;
    }

    public Date getVertrekDatum() {
        return vertrekDatum;
    }

    public void setVertrekDatum(Date vertrekDatum) {
        this.vertrekDatum = vertrekDatum;
    }

    public Date getAankomstDatum() {
        return aankomstDatum;
    }

    public void setAankomstDatum(Date aankomstDatum) {
        this.aankomstDatum = aankomstDatum;
    }

    public String getVertrekLocatie() {
        return vertrekLocatie;
    }

    public void setVertrekLocatie(String vertrekLocatie) {
        this.vertrekLocatie = vertrekLocatie;
    }

    public String getAanKomstLocatie() {
        return aanKomstLocatie;
    }

    public void setAanKomstLocatie(String aanKomstLocatie) {
        this.aanKomstLocatie = aanKomstLocatie;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }
}
