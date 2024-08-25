package com.team.team.itemProcessor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.team.team.entity.Employee;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EmployeeItemProcessor implements ItemProcessor<Employee, Employee> {

    @Override
    public Employee process(@NonNull Employee employee) throws Exception {
        log.info("in processor {}", employee);
        System.out.println(employee);

        return employee;
    }
}
