package com.team.team.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.team.team.dto.EmployeeDTO;
import com.team.team.dto.LoginUserDTO;
import com.team.team.entity.Employee;
import com.team.team.repository.TeamRepository;

@Service
public class AuthenticateService {
    private final TeamRepository teamRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private AuthenticateService(TeamRepository teamRepository, PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager) {
        this.teamRepository = teamRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public Employee signup(EmployeeDTO employeeDTORequest) {
        Employee employee = Employee.builder()
                .name(employeeDTORequest.getName())
                .dob(employeeDTORequest.getDob())
                .doj(employeeDTORequest.getDoj())
                .emailID(employeeDTORequest.getEmailId())
                .squad(employeeDTORequest.getSquad())
                .phoneNumber(employeeDTORequest.getPhoneNumber())
                .password(passwordEncoder.encode(employeeDTORequest.getPassword()))
                .build();
        return teamRepository.save(employee);
    }

    public Employee authenticate(LoginUserDTO loginUserDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUserDTO.getEmail(), loginUserDTO.getPassword()));
        return teamRepository.findByEmailID(loginUserDTO.getEmail()).orElseThrow();
    }
}
