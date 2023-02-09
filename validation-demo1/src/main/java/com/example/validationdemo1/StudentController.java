package com.example.validationdemo1;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor

public class StudentController {
    
    private final StudentService studentService;

    @PostMapping
    public Student createStudent(@Valid @RequestBody Student student) {
        return studentService.save(student);
    }
}
