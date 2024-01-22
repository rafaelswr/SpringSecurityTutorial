package com.security.rafaelswr.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class EmployeeDto {

    private String name;
    private LocalDate birth;
    private String username;
    private String email;
    private int age;

    public EmployeeDto(String name, LocalDate birth, String username, int age, String email) {
        this.name = name;
        this.birth = birth;
        this.username = username;
        this.age = age;
        this.email = email;
    }


}
