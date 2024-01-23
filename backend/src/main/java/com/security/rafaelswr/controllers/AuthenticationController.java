package com.security.rafaelswr.controllers;

import com.security.rafaelswr.models.Employee;
import com.security.rafaelswr.models.EmployeeInfo;
import com.security.rafaelswr.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("")
    public String acessAuth(){
        return "Permit all access on /auth/**";
    }

}
