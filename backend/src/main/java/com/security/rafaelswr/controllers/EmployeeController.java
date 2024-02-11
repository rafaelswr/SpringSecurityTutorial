package com.security.rafaelswr.controllers;

import com.security.rafaelswr.models.Employee;
import com.security.rafaelswr.models.EmployeeDto;
import com.security.rafaelswr.models.EmployeeInfo;
import com.security.rafaelswr.services.AuthenticationService;
import com.security.rafaelswr.services.EmployeeServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class EmployeeController {

    private final AuthenticationService authenticationService;
    private final EmployeeServices employeeServices;

    public EmployeeController(AuthenticationService authenticationService, EmployeeServices employeeServices) {
        this.authenticationService = authenticationService;
        this.employeeServices = employeeServices;
    }

    @GetMapping("/employee")
    public String employee(){
        return " Employee Access !";
    }

    @GetMapping("/admin")
    public String admin(){
        return "Admin Access !";
    }
    /*
       @GetMapping("/employee")
       public ResponseEntity<List<Employee>> getAllEmployees(){
              return new ResponseEntity<>(employeeServices.getAll(), HttpStatus.OK);
        }
    */
    @GetMapping("/employee/all")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<Employee> employees = employeeServices.getAll();
        List<EmployeeDto> combinedDTOs = employees.stream()
                .map(employeeServices::convertToEmployeeDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(combinedDTOs, HttpStatus.OK);

    }

    @DeleteMapping("/employee/delete/{id}")
    public void deleteEmployee(@PathVariable Long id) throws Exception {
        employeeServices.deleteEmployee(id);
    }

    @GetMapping("/employee/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Long id) throws Exception {
        return employeeServices.getEmployeeById(id);
    }


    @GetMapping("/employee/roles/{role}")
    public List<Employee> getAllByRole(@PathVariable String role){
       return  employeeServices.findAllByRole(role);
    }


}

