package com.example.triptop.dto;

public class CheckRequest {
    private String username;
    private String application; // new field

    public CheckRequest() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }
}
