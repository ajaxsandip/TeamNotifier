package com.team.team.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.team.team.dto.EmployeeDTO;
import com.team.team.entity.Employee;
import com.team.team.repository.TeamRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final TeamRepository teamRepository;

    public Employee saveEmployee(EmployeeDTO employeeDTORequest) {
        List<Long> phoneNumbers = Optional.ofNullable(employeeDTORequest.getPhoneNumbers()).map(List::stream)
                .orElseGet(Stream::empty).collect(Collectors.toList());
        Employee employee = Employee.builder()
                .name(employeeDTORequest.getName())
                .dob(employeeDTORequest.getDob())
                .doj(employeeDTORequest.getDoj())
                .emailID(employeeDTORequest.getEmailId())
                .squad(employeeDTORequest.getSquad())
                .phoneNumbers(phoneNumbers)
                .build();
        return teamRepository.save(employee);
    }

    public List<EmployeeDTO> getEmployees() {
        List<Employee> employeeList = teamRepository.findAll();
        return employeeList.stream().map(this::buildEmployeeDTO).toList();
    }

    private EmployeeDTO buildEmployeeDTO(Employee employee) {
        List<Long> phoneNumbers = Optional.ofNullable(employee.getPhoneNumbers()).map(List::stream)
        .orElseGet(Stream::empty).collect(Collectors.toList());
        return EmployeeDTO.builder()
                .name(employee.getName())
                .dob(employee.getDob())
                .doj(employee.getDoj())
                .emailId(employee.getEmailID())
                .squad(employee.getSquad())
                .phoneNumbers(phoneNumbers)
                .build();
    }

}
