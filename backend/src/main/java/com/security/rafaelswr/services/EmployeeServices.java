package com.security.rafaelswr.services;

import com.security.rafaelswr.models.Employee;
import com.security.rafaelswr.models.EmployeeDto;
import com.security.rafaelswr.models.EmployeeInfo;
import com.security.rafaelswr.models.Role;
import com.security.rafaelswr.repositories.EmployeeRepository;
import com.security.rafaelswr.repositories.RoleRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class EmployeeServices implements UserDetailsService {
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;

    public EmployeeServices(EmployeeRepository employeeRepository, RoleRepository roleRepository) {
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
    }

    public List<Employee> getAll() {
       return employeeRepository.findAll();
    }

    public void deleteEmployee(Long id) throws Exception {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()){
            employeeRepository.deleteById(employee.get().getEmployeeId());
        }else{ throw new Exception("Employee Id: "+ id +" doesnt exist!");

        }
    }

    public EmployeeDto getEmployeeById(Long id) throws Exception {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()){
            return convertToEmployeeDTO(employee.get());
        }else{
            throw new Exception("Not found ");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return employeeRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user is not valid"));
    }

    public EmployeeDto convertToEmployeeDTO(Employee employee) {
        if(employee != null){
            EmployeeInfo employeeInfo = (EmployeeInfo) employee;
            return new EmployeeDto(
                    employeeInfo.getName(),
                    employeeInfo.getBirth(),
                    employeeInfo.getUsername(),
                    employeeInfo.getAge(),
                    employeeInfo.getEmail(),
                    employeeInfo.getAuthorities()
            );
        }else{
            return new EmployeeDto(
                    null,
                    null,
                    employee.getUsername(),
                    0,
                    null,
                    null
            );
        }

    }

    public List<Employee> findAllByRole(String role) {
       // return employeeRepository.findAll().stream().filter(p -> p.getAuthority().equals(role)).collect(toList());
        return employeeRepository.findAllByRole(role);
    }

}
