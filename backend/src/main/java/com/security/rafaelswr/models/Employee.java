package com.security.rafaelswr.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "employee")
@Inheritance(strategy = InheritanceType.JOINED)
public class Employee implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(unique = true)
    private String username;

    private String password;
    @Transient
    private String authority;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "employee_role",
            joinColumns = {@JoinColumn(name = "employee_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    //@JsonManagedReference
    private Set<Role> authorities = new HashSet<>();

    public Employee() {
        super();
    }

    public Employee(String username, String password, String authority) {
        this.username = username;
        this.password = password;
        this.authority = authority;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String toString(){
        return "Username: "+this.getUsername()+"\nPassword: "+this.getPassword();
    }
}
