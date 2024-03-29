package com.security.rafaelswr.services;

import com.security.rafaelswr.models.Employee;
import com.security.rafaelswr.models.EmployeeInfo;
import com.security.rafaelswr.models.LoginResponseDto;
import com.security.rafaelswr.models.Role;
import com.security.rafaelswr.repositories.EmployeeRepository;
import com.security.rafaelswr.repositories.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Transactional
public class AuthenticationService {
    private EmployeeRepository employeeRepository;
    private RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    public AuthenticationService(EmployeeRepository employeeRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Employee registerUser(EmployeeInfo emp) throws Exception {
        Optional<EmployeeInfo> existingEmployee = employeeRepository.findByEmail(emp.getEmail());

        if (existingEmployee.isPresent()) {
            throw new Exception("Employee email already exists!");
        } else {

           emp.setPassword(passwordEncoder.encode(emp.getPassword()));

            Optional<Role> findRole = roleRepository.findByAuthority(emp.getAuthority());
            if (findRole.isPresent()){
                emp.getAuthorities().add(findRole.get());
            } else {
                Role r = new Role(emp.getAuthority());
                emp.getAuthorities().add(r);
                roleRepository.save(r);
            }


         return employeeRepository.save(emp);
            /*
            -TEST VERSION

            for (Role r : emp.getAuthorities()) {
                Optional<Role> existingRole = roleRepository.findByAuthority(r.getAuthority());

                System.out.println("AUTHORITIES: " + r);
                System.out.println("ROLE EXISTS: " + existingRole);

                if (existingRole.isPresent()) {
                    // Role já existe, utiliza a instância existente
                    Role role = existingRole.get();
                    emp.getAuthorities().add(role);
                    role.getEmployees().add(emp);
                } else {
                    // Role não existe, salva antes de associar ao Employee
                    roleRepository.save(r);
                    emp.getAuthorities().add(r);
                }
                }
            emp.setPassword(encoder.encode(emp.getPassword()));
            employeeRepository.save(emp);
            -----------------------------------------
            */

        }

    }

    //take the auth and check username & password, then
    //generate the JWT token
    public LoginResponseDto loginUser(String username, String password){
        try {

            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(auth);

            return new LoginResponseDto(employeeRepository.findByUsername(username).get(), token);

        }catch (AuthenticationException e){
            return new LoginResponseDto(null, "");
        }

    }
}

