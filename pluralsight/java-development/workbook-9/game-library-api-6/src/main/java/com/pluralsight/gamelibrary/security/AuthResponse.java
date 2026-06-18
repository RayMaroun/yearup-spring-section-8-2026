package com.pluralsight.gamelibrary.security;

// What we send back: the signed JWT to use on later requests.
public class AuthResponse {

    private String token;

    public AuthResponse() { }

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}
