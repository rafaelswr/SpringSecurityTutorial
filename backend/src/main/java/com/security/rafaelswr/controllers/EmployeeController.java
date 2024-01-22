package com.security.rafaelswr.controllers;

import com.security.rafaelswr.models.Employee;
import com.security.rafaelswr.models.EmployeeDto;
import com.security.rafaelswr.models.EmployeeInfo;
import com.security.rafaelswr.services.EmployeeServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class EmployeeController {

    private final EmployeeServices employeeServices;

    public EmployeeController(EmployeeServices employeeServices) {
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

    @PostMapping("/employees/add")
    public void addEmployee(@RequestBody EmployeeInfo emp) throws Exception {
        employeeServices.saveNewEmployee(emp);
    }

    /*
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeInfo>> getAllEmployees(){
           return new ResponseEntity<>(employeeServices.getAll(), HttpStatus.OK);
     }
    */
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<Employee> employees = employeeServices.getAll();
        List<EmployeeDto> combinedDTOs = employees.stream()
                .map(employeeServices::convertToEmployeeDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(combinedDTOs, HttpStatus.OK);

    }


    @DeleteMapping("/employees/delete/{id}")
    public void deleteEmployee(@PathVariable Long id) throws Exception {
        employeeServices.deleteEmployee(id);
    }

    @GetMapping("/employees/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Long id) throws Exception {
        return employeeServices.getEmployeeById(id);
    }



}

