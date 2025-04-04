package com.example.triptop.dto;

import kong.unirest.json.JSONObject;

public class CheckResponse {
    private String access;
    private String role;

    public CheckResponse() {}

    public CheckResponse(String access, String role) {
        this.access = access;
        this.role = role;
    }

    public CheckResponse(JSONObject json) {
        this.access = json.optString("access", "denied");
        this.role = json.optString("role", "");
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
