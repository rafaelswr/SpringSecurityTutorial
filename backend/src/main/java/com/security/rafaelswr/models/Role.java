package com.security.rafaelswr.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;


import java.net.PasswordAuthentication;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

    private String authority;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Employee> employees;

    public Role(String authority){
        this.authority = authority;
        this.employees = new HashSet<>();
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

}
