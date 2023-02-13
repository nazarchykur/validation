package com.example.validationdemo3;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class CustomGlobalExceptionHandler {
    
    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleNotFound(BookNotFoundException exception) {
        Map<String, Object> errorMap = new LinkedHashMap<>();
        errorMap.put("timestamp", LocalDateTime.now());
        errorMap.put("status", HttpStatus.NOT_FOUND.value());
        errorMap.put("errors", Collections.singletonList(exception.getMessage()));

        return errorMap;
    }

    // @Validate For Validating Path Variables and Request Parameters
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> constraintViolationException(ConstraintViolationException exception) {
        Map<String, Object> errorMap = new LinkedHashMap<>();
        errorMap.put("timestamp", LocalDateTime.now());
        errorMap.put("status", HttpStatus.BAD_REQUEST.value());
        errorMap.put("errors", Collections.singletonList(exception.getMessage()));

        return errorMap;
    }
    
    @ExceptionHandler(BookUnsupportedFieldPatchException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Map<String, Object> unsupportedFieldPatch(BookUnsupportedFieldPatchException exception) {
        Map<String, Object> errorMap = new LinkedHashMap<>();
        errorMap.put("timestamp", LocalDateTime.now());
        errorMap.put("status", HttpStatus.METHOD_NOT_ALLOWED.value());
        errorMap.put("errors", Collections.singletonList(exception.getMessage()));

        return errorMap;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleMethodArgumentException(MethodArgumentNotValidException exception) {
        Map<String, Object> errorMap = new LinkedHashMap<>();
        errorMap.put("timestamp", LocalDateTime.now());
        errorMap.put("status", HttpStatus.BAD_REQUEST.value());

        // if we just create a list of errors and return only them
//        exception.getBindingResult().getFieldErrors()
//                .forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));

        //Get all errors
        List<String> errors = exception.getBindingResult().getFieldErrors().stream()
                .map(x -> x.getDefaultMessage())
                .toList();

        errorMap.put("errors", errors);

        return errorMap;
    }
}
