package com.team.team.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
// import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
// import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.transaction.PlatformTransactionManager;

import com.team.team.entity.Employee;
import com.team.team.itemProcessor.EmployeeItemProcessor;
// import com.team.team.reader.EmployeeDetailsReader;
import com.team.team.writer.EmployeeItemWriter;

// import jakarta.activation.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableBatchProcessing
// @Slf4j
// @RequiredArgsConstructor
public class BatchJobconfiguration {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;




    @Bean
    public Job employeeJob(Step step1){
        // return new JobBuilder("employeeJobBuilder")
        //     .incrementer(new RunIdIncrementer())
        //     .start(step1)
        //     .build();
        return jobBuilderFactory.get("employeeJobBuilder").start(step1).build();
    }

    
    @Bean
    public Step step1(FlatFileItemReader<Employee> employeeDetailsReader,EmployeeItemProcessor employeeItemProcessor,EmployeeItemWriter employeeItemWriter){
        // return new StepBuilder("EmployeeData")
        //     .<Employee,Employee>chunk(10)
        //     .reader(employeeDetailsReader)
        //     .processor(employeeItemProcessor)
        //     .writer(employeeItemWriter)
        //     .build();
        return stepBuilderFactory.get("step1")
                .<Employee,Employee>chunk(10)
                .reader(employeeDetailsReader)
                .processor(employeeItemProcessor)
                .writer(employeeItemWriter)
                .build();

    }

}
