package com.example.auth_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDTO {

    private String username;
    private String password;
    private String confirmPassword;

    public UserRegistrationDTO() {}

    public UserRegistrationDTO(String username, String password, String confirmPassword) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
