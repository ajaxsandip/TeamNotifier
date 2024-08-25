package com.team.team.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        List<Long> phoneNumbers = Optional.ofNullable(employeeDTORequest.getPhoneNumbers()).map(List::stream)
                .orElseGet(Stream::empty).collect(Collectors.toList());
        Employee employee = Employee.builder()
                .name(employeeDTORequest.getName())
                .dob(employeeDTORequest.getDob())
                .doj(employeeDTORequest.getDoj())
                .emailID(employeeDTORequest.getEmailId())
                .squad(employeeDTORequest.getSquad())
                .phoneNumbers(phoneNumbers)
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
