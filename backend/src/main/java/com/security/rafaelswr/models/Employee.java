package com.security.rafaelswr.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "employee")
@Inheritance(strategy = InheritanceType.JOINED)
public class Employee{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    private String username;
    private String password;



    public Employee() {
        super();
    }

    public Employee(String username, String password) {
        this.username = username;
        this.password = password;

    }

}
