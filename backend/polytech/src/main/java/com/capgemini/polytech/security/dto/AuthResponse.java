package com.capgemini.polytech.security.dto;

import lombok.Data;

@Data
public class AuthResponse {
    private String accessToken;
    //    private String refreshToken;
    //    private String tokenType = "Bearer ";
    
    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    //   this.refreshToken = refreshToken;
        }

    
}
