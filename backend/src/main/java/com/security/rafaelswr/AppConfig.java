package com.security.rafaelswr;

import com.security.rafaelswr.models.Employee;
import com.security.rafaelswr.models.EmployeeInfo;
import com.security.rafaelswr.models.Role;
import com.security.rafaelswr.repositories.EmployeeRepository;
import com.security.rafaelswr.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class AppConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner commandLineRunner (EmployeeRepository employeeRepository, RoleRepository roleRepository){
        return args -> {

            Role user_role = new Role("USER");
            Role admin_role = new Role("ADMIN");

            Employee rafa = new EmployeeInfo("rafaelswr", passwordEncoder.encode("1223"), "Rafa","rafa@gmail.com", LocalDate.of(1999,1,13), "USER");
            Employee ines = new EmployeeInfo("Ines", passwordEncoder.encode("1223"), "ines","ines@gmail.com", LocalDate.of(1999,9,9), "ADMIN");

            user_role.getEmployees().add(rafa);
            admin_role.getEmployees().add(ines);

            rafa.getAuthorities().add(user_role);
            ines.getAuthorities().add(admin_role);

            employeeRepository.save(rafa);
            employeeRepository.save(ines);

            roleRepository.save(user_role);
            roleRepository.save(admin_role);
        };
    }
}
