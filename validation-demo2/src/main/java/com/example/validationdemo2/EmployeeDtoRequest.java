package com.example.validationdemo2;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeDtoRequest {
    @NotBlank(message = "firstName shouldn't be null or empty")
    private String firstName;
    
//    @NotBlank(message = "lastName shouldn't be null or empty") == @NotNull + @NotEmpty
    @NotNull(message = "lastName shouldn't be null")
    @NotEmpty(message = "lastName shouldn't be empty")
    private String lastName;

    @ValidateEmployeeType // custom validation
    private String employeeType; // permanent, vendor or contractor
}
