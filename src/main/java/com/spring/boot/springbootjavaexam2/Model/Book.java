package com.spring.boot.springbootjavaexam2.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    @NotEmpty(message = "ID should not be empty")
    private String iD;

    @NotEmpty(message = "name should not be empty")
    private String name;

    @NotNull(message = "number_of_pages should not be empty")
    @Positive(message = "Pages should not be less than 1")
    private int number_of_pages;

    @NotNull(message = "price should not be empty")
    @PositiveOrZero(message = "The price can not be negative")
    private double price;

    @NotEmpty(message = "category should not be empty")
    @Pattern(regexp = "^(novel|academic)$", message = "category should be either novel or academic")
    private String category;

    @NotNull(message = "isAvailable should not be empty")
    private boolean isAvailable;

}
