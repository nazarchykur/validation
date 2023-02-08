package com.example.validationdemo1;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class ApiError {
    private final HttpStatus status;
    private final String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime time = LocalDateTime.now();

    @Setter
    private Map<String, String> validationErrors;

    public ApiError(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}