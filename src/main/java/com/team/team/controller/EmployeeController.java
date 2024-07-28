package com.team.team.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.team.dto.EmployeeDTO;
import com.team.team.entity.Employee;
import com.team.team.service.EmployeeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;

@RequestMapping("/v1/employees")
@RestController
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("save")
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody EmployeeDTO employeeRequest) {
        return new ResponseEntity<>(employeeService.saveEmployee(employeeRequest), HttpStatus.CREATED);
    }

    @GetMapping("findAll")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getEmployees(),HttpStatus.OK);
    }
    
    
}
