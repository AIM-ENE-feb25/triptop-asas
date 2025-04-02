package com.example.sso.domain;

import javax.persistence.*;

@Entity
@Table(name = "sso_users")
public class SsoUser {

    @Id
    @Column(name = "gebruikersnaam")
    private String username;

    @Column(name = "voornaam")
    private String voornaam;

    @Column(name = "achternaam")
    private String achternaam;

    @Column(name = "wachtwoord")
    private String wachtwoord;

    // Constructors, getters and setters

    public SsoUser() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }
}
