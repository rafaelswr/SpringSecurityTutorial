package com.security.rafaelswr.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
public class EmployeeDto {

    private String name;
    private LocalDate birth;
    private String username;
    private String email;
    private int age;
    private Set<Role> authorities;

    public EmployeeDto(String name, LocalDate birth, String username, int age, String email,Set<Role> authorities) {
        this.name = name;
        this.birth = birth;
        this.username = username;
        this.age = age;
        this.email = email;
        this.authorities = authorities;
    }


}
