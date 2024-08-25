package com.team.team.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.team.team.entity.Employee;

public class EmployeeCSVMapper implements FieldSetMapper<Employee> {

    @Override
    public Employee mapFieldSet(FieldSet fieldSet) throws BindException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dob = LocalDate.parse(fieldSet.readString("DOB"), formatter);
        LocalDate doj = LocalDate.parse(fieldSet.readString("DOJ"), formatter);
        String phoneNumbers = fieldSet.readString("PhoneNumber");
        List<Long> phoneNumbersList = Arrays.stream(phoneNumbers.split(",")).map(String::trim).map(Long::parseLong)
                .collect(Collectors.toList());
        Employee employee = new Employee();
        employee.setEmployeeID(fieldSet.readLong("EmployeeID"));
        employee.setName(fieldSet.readString("FirstName") + fieldSet.readLong("LastName"));
        employee.setSquad(fieldSet.readString("Squad"));
        employee.setDob(dob);
        employee.setDoj(doj);
        employee.setEmailID(fieldSet.readString("Email"));
        employee.setPhoneNumbers(phoneNumbersList);
        return employee;
    }
}
