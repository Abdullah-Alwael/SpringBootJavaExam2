package com.spring.boot.springbootjavaexam2.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    private int number_of_pages;

    @NotNull(message = "price should not be empty")
    private double price;

    @NotEmpty(message = "category should not be empty")
    @Pattern(regexp = "^(novel|academic)$", message = "category should be either novel or academic")
    private String category;

    @NotNull(message = "isAvailable should not be empty")
    private boolean isAvailable;

}
