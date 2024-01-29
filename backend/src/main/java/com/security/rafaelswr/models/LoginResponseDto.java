package com.security.rafaelswr.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponseDto {

    private Employee user;
    private String jwt;

    public LoginResponseDto(Employee user, String jwt) {
        this.user = user;
        this.jwt = jwt;
    }
}
