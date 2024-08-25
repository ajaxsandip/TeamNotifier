package com.team.team.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO {
    @NotBlank(message = "Name is Required.")
    private String name;
    @NotBlank(message = "Squad is Required.")
    private String squad;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "DOB is Required.")
    private LocalDate dob;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "DOJ is Required.")
    private LocalDate doj;
    @Email(message = "The email is invalid")
    @NotBlank(message = "Email is Required.")
    private String emailId;
    @NotBlank(message = "Phone Number is Required.")
    private List<Long> phoneNumbers;
    @NotBlank(message = "Password is Required.")
    private String password;
    
    
}
