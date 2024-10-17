package com.example.auth_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Long id;
    private String username;
    private String roles;

    public UserDTO() {}

    public UserDTO(Long id, String username, String roles) {
        this.id = id;
        this.username = username;
        this.roles = roles;
    }
}

