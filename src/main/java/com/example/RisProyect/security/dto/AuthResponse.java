package com.example.practica_completa.security.dto;

import com.example.RisProyect.user.model.User;

public class AuthResponse {
    private String jwt;
    private Long userId;
    private String username;
    private long expiration;

    public AuthResponse(String jwt, Class<? extends User> userId, String username, long expiration) {
        this.jwt = jwt;
        this.userId = userId;
        this.username = username;
        this.expiration = expiration;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getExpiration() {
        return expiration;
    }

    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }
}
