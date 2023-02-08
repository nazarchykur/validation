package com.example.validationdemo1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    
    private final StudentRepository studentRepository;


    public Student save(Student student) {
        return studentRepository.save(student);
    }
}
