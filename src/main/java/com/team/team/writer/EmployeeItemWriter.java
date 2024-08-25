package com.team.team.writer;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.team.team.entity.Employee;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EmployeeItemWriter implements ItemWriter<Employee> {

    @Override
    public void write(@NonNull Chunk<? extends Employee> employee) throws Exception {
        log.info("chunk {}",employee);
        System.out.println(employee);
    }
    
}
