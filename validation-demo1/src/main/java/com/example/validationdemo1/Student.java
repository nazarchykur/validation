package com.example.validationdemo1;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "student")
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @NotNull // using from jakarta.validation.constraints.NotNull;
    @Size(min = 3, max = 15)
    @Column(name = "username")
    private String username;

    @NotNull
    @Email
    @Column(name = "email")
    private String email;

    @NotBlank
    @Column(name = "password")
    private String password;

}

/*

    https://jakarta.ee/specifications/bean-validation/3.0/apidocs/jakarta/validation/constraints/package-summary.html
    
   
    ... 
    
    most popular:

    +----------------------+------------------------------------------------------------------------------------------------------+
    | AssertFalse          | The annotated element must be false.                                                                 |
    +----------------------+------------------------------------------------------------------------------------------------------+
    | AssertTrue           | The annotated element must be true.                                                                  |
    +----------------------+------------------------------------------------------------------------------------------------------+
    | Digits               | The annotated element must be a number within accepted range.                                        |
    +----------------------+------------------------------------------------------------------------------------------------------+
    | Email                | The string has to be a well-formed email address.                                                    |
    +----------------------+------------------------------------------------------------------------------------------------------+
    | Future               | The annotated element must be an instant, date or time in the future.                                |
    +----------------------+------------------------------------------------------------------------------------------------------+
    | FutureOrPresent      | The annotated element must be an instant, date or time in the present or in the future.              |
    +----------------------+------------------------------------------------------------------------------------------------------+
    | Max                  | The annotated element must be a number whose value must be lower or equal to the specified maximum.  |
    +----------------------+------------------------------------------------------------------------------------------------------+
    | Min                  | The annotated element must be a number whose value must be higher or equal to the specified minimum. |
    +----------------------+------------------------------------------------------------------------------------------------------+
    | NegativeOrZero       | The annotated element must be a negative number or 0.                                                |
    +----------------------+------------------------------------------------------------------------------------------------------+
    | NotBlank             | The annotated element must not be null and must contain at least one non-whitespace character.       |
    +----------------------+------------------------------------------------------------------------------------------------------+
    | NotEmpty             | The annotated element must not be null nor empty.                                                    |
    +----------------------+------------------------------------------------------------------------------------------------------+
    | NotNull              | The annotated element must not be null.                                                              |
    +----------------------+------------------------------------------------------------------------------------------------------+
    | Null                 | The annotated element must be null.                                                                  |
    +----------------------+------------------------------------------------------------------------------------------------------+
    | Past                 | The annotated element must be an instant, date or time in the past.                                  |
    +----------------------+------------------------------------------------------------------------------------------------------+
    | Pattern              | The annotated CharSequence must match the specified regular expression.                              |
    +----------------------+------------------------------------------------------------------------------------------------------+
    | Size                 | The annotated element size must be between the specified boundaries (included).                      |
    +----------------------+------------------------------------------------------------------------------------------------------+

...

 */