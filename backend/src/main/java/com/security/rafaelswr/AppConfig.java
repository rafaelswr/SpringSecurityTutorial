package com.security.rafaelswr;

import com.security.rafaelswr.models.Employee;
import com.security.rafaelswr.models.EmployeeInfo;
import com.security.rafaelswr.models.Role;
import com.security.rafaelswr.repositories.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class AppConfig {
    @Bean
    CommandLineRunner commandLineRunner (EmployeeRepository employeeRepository){
        return args -> {
            Employee emp = new EmployeeInfo("rafaelswr", "1223", "Rafa","rafaels.borges91@gmial.com", LocalDate.of(1999,1,13));
            employeeRepository.save(emp);



        };
    }

}
