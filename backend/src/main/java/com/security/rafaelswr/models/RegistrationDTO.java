package com.security.rafaelswr.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistrationDTO {

    private String username;
    private String password;

    public RegistrationDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }


}
