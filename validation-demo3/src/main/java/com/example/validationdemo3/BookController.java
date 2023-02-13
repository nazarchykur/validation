package com.example.validationdemo3;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    
    private final BookRepository bookRepository;

    // Find
    @GetMapping
    List<Book> findAll() {
        return bookRepository.findAll();
    }

    // Save
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Book newBook(@Valid @RequestBody Book newBook) {
        return bookRepository.save(newBook);
    }

    // Find
    @GetMapping("/{id}")
    Book findOne(@PathVariable @Min(1) Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }
}

/*
    Bean Validation (Hibernate Validator)
    
    1. The bean validation will be enabled automatically if any JSR-303 implementation (like Hibernate Validator) is 
        available on the classpath. By default, Spring Boot will get and download the Hibernate Validator automatically.

    2. The below POST request will be passed, we need to implement the bean validation on the book object to make sure 
        fields like name, author and price are not empty.
    
    3. Annotate the bean with javax... or   jakarta.validation.constraints.* annotations.
        @NotEmpty
        @NotEmpty(message = "Please provide a name")
        @NotNull
        @DecimalMin("1.00")
        @Min / @Max
        ...
        
    4. Add @Valid to @RequestBody. Done, bean validation is enabled now.
        @PostMapping("/books")
        Book newBook(@Valid @RequestBody Book newBook) {
            return repository.save(newBook);
        }
    
    5. Try to send a POST request to the REST endpoint again. If the bean validation is failed, it will trigger a 
        MethodArgumentNotValidException. By default, Spring will send back an HTTP status 400 Bad Request, but no error detail.
            
                {
                    "timestamp": "2023-02-10T17:59:42.329+00:00",
                    "status": 400,
                    "error": "Bad Request",
                    "path": "/books"
                }

     6. The above error response is not friendly, we can catch the MethodArgumentNotValidException and override the 
        response like this :
        
                {
                    "timestamp": "2023-02-10T20:03:12.189541677",
                    "status": "BAD_REQUEST",
                    "errors": [
                        "Author is not allowed."
                    ]
                }
       
       
       ----------------------------------------------------------------------------------------------------------------
    2. Path Variables Validation
    
    2.1. We also can apply the javax or jakarta.validation.constraints.* annotations on the path variable or even the request 
        parameter directly.
    
    2.2. Apply @Validated on class level, and add the javax.validation.constraints.* annotations on path variables like this :
           
            @RestController
            @Validated // class level                                                    <= 
            public class BookController {
            
                @GetMapping("/books/{id}")
                Book findOne(@PathVariable @Min(1) Long id) { //jsr 303 annotations      <= 
                    return repository.findById(id)
                            .orElseThrow(() -> new BookNotFoundException(id));
                }
            
                //...
            }
            
            
     2.3. The default error message is good, just the error code 500 is not suitable.
            {
                "timestamp": "2023-02-10T18:07:43.030+00:00",
                "status": 500,
                "error": "Internal Server Error",
                "path": "/books/0"
            }
            
            
      
      2.4. If the @Validated is failed, it will trigger a ConstraintViolationException, we can override the error 
            code like this :
            
            {
                "timestamp": "2023-02-10T20:11:35.424795448",
                "status": "BAD_REQUEST",
                "errors": [
                    "findOne.id: must be greater than or equal to 1"
                ]
            }
            
            
 ----------------------------------------------------------------------------------------------------------------------
    Custom Validator
    
    3.1. We will create a custom validator for the author field, only allowing 4 authors to save into the database.
    
        @Target({FIELD})
        @Retention(RUNTIME)
        @Constraint(validatedBy = AuthorValidator.class)
        @Documented
        public @interface Author {
        
            String message() default "Author is not allowed.";
        
            Class<?>[] groups() default {};
        
            Class<? extends Payload>[] payload() default {};
        
        }




        public class AuthorValidator implements ConstraintValidator<Author, String> {
            List<String> authors = Arrays.asList("leo", "donnie", "mikey", "raph");
        
            @Override
            public boolean isValid(String value, ConstraintValidatorContext context) {
                return authors.contains(value);
            }
        }
        
        
        @Entity
        public class Book {
        
            @Author
            @NotEmpty(message = "Please provide a author")
            private String author;
            
            //...   
       
 */