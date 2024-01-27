package com.security.rafaelswr.repositories;

import com.security.rafaelswr.models.Employee;
import com.security.rafaelswr.models.EmployeeInfo;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT s from EmployeeInfo s where s.email = ?1")
    Optional<EmployeeInfo> findByEmail(String email);

    Optional<Employee> findByUsername(String username);

}
