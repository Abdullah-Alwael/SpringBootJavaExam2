package com.spring.boot.springbootjavaexam2.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    @NotEmpty(message = "ID should not be empty")
    private String iD;

    @NotEmpty(message = "name should not be empty")
    private String name;

    @NotNull(message = "age should not be empty")
    @Positive(message = "Age can not be negative")
    private int age;

    @NotNull(message = "balance should not be empty")
    @PositiveOrZero(message = "balance should be a valid positive number or zero")
    private double balance;

    @NotEmpty(message = "role should not be empty")
    @Pattern(regexp = "^(customer|librarian)$", message = "user role should be either customer or librarian")
    private String role;

}
