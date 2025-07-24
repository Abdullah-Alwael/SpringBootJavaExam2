package com.spring.boot.springbootjavaexam2.Controller;

import com.spring.boot.springbootjavaexam2.Api.ApiResponse;
import com.spring.boot.springbootjavaexam2.Model.User;
import com.spring.boot.springbootjavaexam2.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService = new UserService();

    @GetMapping("/list")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }

    @PostMapping("/new")
    public ResponseEntity<?> addUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
                    ApiResponse(errors.getFieldError().getDefaultMessage()));
        }

        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("User added successfully"));

    }

    @PutMapping("/update/{iD}")
    public ResponseEntity<?> updateUser(@PathVariable String iD, @Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
                    ApiResponse(errors.getFieldError().getDefaultMessage()));
        }

        if (userService.updateUser(iD, user)) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("User updated successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error, user not found"));
        }
    }

    @DeleteMapping("/delete/{iD}")
    public ResponseEntity<?> deleteUser(@PathVariable String iD) {
        if (userService.deleteUser(iD)) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("User deleted successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error, user not found"));
        }
    }

    @GetMapping("/filter/balance/{balance}")
    public ResponseEntity<?> filterUsersByBalance(@PathVariable double balance) {
        if (balance < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Error," +
                    " balance can not be negative"));
        }

        ArrayList<User> usersByBalance = userService.usersAboveBalance(balance);
        if (usersByBalance.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error, not found"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(usersByBalance);
    }

    @GetMapping("/filter/age/{age}")
    public ResponseEntity<?> filterUsersByAge(@PathVariable int age) {
        if (age < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Error," +
                    " age can not be negative"));
        }

        ArrayList<User> usersByAge = userService.usersAboveAge(age);

        if (usersByAge.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error, not found"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(usersByAge);
    }

}
