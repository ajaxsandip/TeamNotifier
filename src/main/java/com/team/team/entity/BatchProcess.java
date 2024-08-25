package com.team.team.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "batch_process")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BatchProcess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String process_name;
    private LocalDate start_timestamp;
    private LocalDate end_timestamp;
    private String processed_file_name;
    private Long inserted_record_count;
    private Long updated_record_count;
    private Long errored_record_count;
}
