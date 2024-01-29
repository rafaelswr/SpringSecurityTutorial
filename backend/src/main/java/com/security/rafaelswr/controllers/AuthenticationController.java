package com.security.rafaelswr.controllers;

import com.security.rafaelswr.models.Employee;
import com.security.rafaelswr.models.EmployeeInfo;
import com.security.rafaelswr.models.LoginResponseDto;
import com.security.rafaelswr.models.RegistrationDTO;
import com.security.rafaelswr.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public Employee registerEmployee(@RequestBody EmployeeInfo employeeInfo) throws Exception {
        return authenticationService.registerUser(employeeInfo);
    }

    @PostMapping("/login")
    public LoginResponseDto loginEmployee(@RequestBody RegistrationDTO employee) throws Exception {
        return authenticationService.loginUser(employee.getUsername(), employee.getPassword());
    }

    @GetMapping("")
    public String acessAuth(){
        return "Permit all access on /auth/**";
    }

}
