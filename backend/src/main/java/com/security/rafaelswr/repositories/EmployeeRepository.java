package com.security.rafaelswr.repositories;

import com.security.rafaelswr.models.Employee;
import com.security.rafaelswr.models.EmployeeInfo;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT s from EmployeeInfo s where s.email = ?1")
    Optional<EmployeeInfo> findByEmail(String email);

    @Query("SELECT s from Employee s JOIN s.authorities r where r.authority = ?1")
    //@Query("SELECT s from Employee s JOIN s.authorities r where r.authority = :role")
    List<Employee> findAllByRole(@Param("role") String role);

    Optional<Employee> findByUsername(String username);
}
