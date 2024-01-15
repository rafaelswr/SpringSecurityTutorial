package com.security.rafaelswr.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Data
@NoArgsConstructor
@Table(name = "employee_info")
public class EmployeeInfo extends Employee{

    private String name;
    private String email;
    private LocalDate birth;

    @Transient
    private String age;

    public int getAge() {
        return Period.between(this.getBirth(), LocalDate.now()).getYears();
    }

    public EmployeeInfo(String username, String pass, String name,String email, LocalDate birth) {
        super(username, pass);
        this.name = name;
        this.birth = birth;
        this.email = email;
    }


}
