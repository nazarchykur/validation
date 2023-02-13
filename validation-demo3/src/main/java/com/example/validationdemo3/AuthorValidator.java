package com.example.validationdemo3;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class AuthorValidator implements ConstraintValidator<Author, String> {

    List<String> authors = Arrays.asList("leo", "donnie", "mikey", "raph");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return authors.contains(value);
    }
}
