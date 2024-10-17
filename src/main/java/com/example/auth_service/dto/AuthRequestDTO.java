package com.example.auth_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequestDTO {

    private String username;
    private String password;

    public AuthRequestDTO() {}

    public AuthRequestDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
