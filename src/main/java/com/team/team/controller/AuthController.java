package com.team.team.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.team.dto.EmployeeDTO;
import com.team.team.dto.JwtAuthResponse;
import com.team.team.dto.LoginUserDTO;
import com.team.team.entity.Employee;
import com.team.team.service.AuthenticateService;
import com.team.team.service.JwtService;
import org.springframework.web.bind.annotation.PostMapping;

@RequestMapping("/auth")
@RestController
public class AuthController {
    private final JwtService jwtService;
    private final AuthenticateService authenticateService;

    private AuthController(JwtService jwtService, AuthenticateService authenticateService) {
        this.jwtService = jwtService;
        this.authenticateService = authenticateService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Employee> registerUser(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = authenticateService.signup(employeeDTO);
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginUserDTO loginUserDTO) {
        Employee authenticatedUser = authenticateService.authenticate(loginUserDTO);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        JwtAuthResponse loginResponse = new JwtAuthResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }

}
