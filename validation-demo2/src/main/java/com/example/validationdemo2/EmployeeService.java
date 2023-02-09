package com.example.validationdemo2;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmployeeService {

    public EmployeeDtoResponse addNewEmployee(EmployeeDtoRequest employeeDto){
        //add employee to database
        
        EmployeeDtoResponse employeeDtoResponse = new EmployeeDtoResponse();
        employeeDtoResponse.setFirstName(employeeDto.getFirstName());
        employeeDtoResponse.setLastName(employeeDto.getLastName());
        employeeDtoResponse.setDate(LocalDateTime.now());
        employeeDtoResponse.setEmployeeType(employeeDto.getEmployeeType());
        
        return employeeDtoResponse;
    }
}