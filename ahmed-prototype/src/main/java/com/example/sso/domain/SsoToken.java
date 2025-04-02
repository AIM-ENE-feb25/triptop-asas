package com.example.sso.domain;

import javax.persistence.*;

@Entity
@Table(name = "sso_tokens")
public class SsoToken {

    @Id
    @Column(name = "gebruikersnaam")
    private String username;

    @Column(name = "access_token")
    private String accessToken;


    public SsoToken() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
