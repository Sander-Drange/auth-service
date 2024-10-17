package com.example.auth_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponseDTO {

    private String token;

    public JwtResponseDTO() {}

    public JwtResponseDTO(String token) {
        this.token = token;
    }
}
