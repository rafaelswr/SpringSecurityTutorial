package com.security.rafaelswr.repositories;

import com.security.rafaelswr.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT s from Employee s where s.email = ?1")
    Optional<Employee> findByEmail(String employee);

}
