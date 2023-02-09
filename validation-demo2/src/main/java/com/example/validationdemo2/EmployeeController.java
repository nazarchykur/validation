package com.example.validationdemo2;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    
    private final EmployeeService service;

    @PostMapping
    public EmployeeDtoResponse addNewEmployee(@Valid @RequestBody EmployeeDtoRequest employeeDto){
        return service.addNewEmployee(employeeDto);
    }
}
