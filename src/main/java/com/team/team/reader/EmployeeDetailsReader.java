package com.team.team.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.team.team.entity.Employee;
import com.team.team.mapper.EmployeeCSVMapper;

@Component
public class EmployeeDetailsReader {
    @Bean
    public FlatFileItemReader<Employee> reader() {
        return new FlatFileItemReaderBuilder<Employee>()
                .name("employeeReader")
                .resource(new ClassPathResource("EmployeeData.csv"))
                .linesToSkip(1)
                .lineMapper(new DefaultLineMapper<Employee>() {
                    {
                        setLineTokenizer(new DelimitedLineTokenizer() {
                            {
                                setNames("EmployeeID", "FirstName", "LastName", "Email", "Squad", "PhoneNumber", "DOB",
                                        "DOJ");
                            }
                        });
                        setFieldSetMapper(new EmployeeCSVMapper());
                    }
                })
                // .delimited()
                // .names(new String[]{"EmployeeID", "FirstName", "LastName", "Email", "Squad", "PhoneNumber", "DOB","DOJ"})
                // .fieldSetMapper(new BeanWrapperFieldSetMapper<Employee>(){{setTargetType(Employee.class);}})
                .build();
    }
}
