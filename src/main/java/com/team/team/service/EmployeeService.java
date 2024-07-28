package com.team.team.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.team.team.dto.EmployeeDTO;
import com.team.team.entity.Employee;
import com.team.team.repository.TeamRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeService {
    
    private final TeamRepository teamRepository;
    
    public Employee saveEmployee(EmployeeDTO employeeDTORequest){
        Employee employee = Employee.builder()
                        .name(employeeDTORequest.getName())
                        .dob(employeeDTORequest.getDob())
                        .doj(employeeDTORequest.getDoj())
                        .emailID(employeeDTORequest.getEmailId())
                        .squad(employeeDTORequest.getSquad())
                        .phoneNumber(employeeDTORequest.getPhoneNumber())
                        .build();
        return teamRepository.save(employee);        
    }

    public List<EmployeeDTO> getEmployees(){
        List<Employee> employeeList = teamRepository.findAll();
        return employeeList.stream().map(this::buildEmployeeDTO).toList();            
    }


    private EmployeeDTO buildEmployeeDTO(Employee employee){
        return EmployeeDTO.builder()
                .name(employee.getName())
                .dob(employee.getDob())
                .doj(employee.getDoj())
                .emailId(employee.getEmailID())
                .squad(employee.getSquad())
                .phoneNumber(employee.getPhoneNumber())
                .build();
    }
    
}
