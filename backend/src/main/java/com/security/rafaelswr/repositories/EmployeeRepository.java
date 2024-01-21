package com.security.rafaelswr.repositories;

import com.security.rafaelswr.models.Employee;
import com.security.rafaelswr.models.EmployeeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT s from EmployeeInfo s where s.email = ?1")
    Optional<Employee> findByEmail(String email);



}
