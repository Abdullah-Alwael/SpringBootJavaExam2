package com.spring.boot.springbootjavaexam2.Controller;

import com.spring.boot.springbootjavaexam2.Api.ApiResponse;
import com.spring.boot.springbootjavaexam2.Model.Book;
import com.spring.boot.springbootjavaexam2.Model.User;
import com.spring.boot.springbootjavaexam2.Service.BookService;
import com.spring.boot.springbootjavaexam2.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService = new BookService();
    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<?> getBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
    }

    @PostMapping("/new")
    public ResponseEntity<?> addNewBook(@Valid @RequestBody Book book, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
                    ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Book added successfully"));
    }

    @PutMapping("/update/{iD}")
    public ResponseEntity<?> updateABook(@PathVariable String iD, @Valid @RequestBody Book book, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
                    ApiResponse(errors.getFieldError().getDefaultMessage()));
        }

        if (bookService.updateBook(iD, book)){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Book deleted successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error, not found"));
        }
    }

    @DeleteMapping("/delete/{iD}")
    public ResponseEntity<?> deleteABook(@PathVariable String iD){
        if (bookService.deleteBook(iD)){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Book deleted successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error, not found"));
        }
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<?> searchBookByName(@PathVariable String name){
        Book bookByName = bookService.searchBookByName(name);

        if (bookByName == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error, book not found"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(bookByName);
    }

    @GetMapping("/filter/category/{category}")
    public ResponseEntity<?> filterByCategory(@PathVariable String category){
        if (!category.matches("^(novel|academic)$")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
                    ApiResponse("category should be either novel or academic"));
        }

        ArrayList<Book> booksByCategory = bookService.searchByCategory(category);

        if (booksByCategory.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error, not found"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(booksByCategory);
    }

    @GetMapping("/filter/number-of-pages/{number}")
    public ResponseEntity<?> filterByCategory(@PathVariable int number){
        if (number<0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
                    ApiResponse("number of pages can not be negative"));
        }

        ArrayList<Book> booksByPages = bookService.searchByNumberOfPages(number);

        if (booksByPages.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error, not found"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(booksByPages);
    }

    @PutMapping("/set-status/unavailable/{iD}")
    public ResponseEntity<?> makeBookUnavailable(@PathVariable String iD, @Valid @RequestBody User user, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
                    ApiResponse(errors.getFieldError().getDefaultMessage()));
        }

        // the array creates a new userService object, hence it becomes empty
//        ArrayList<User> users = userService.getUsers();
//        boolean found = false;
//        for (User u:users){
//            if (u.getID().equals(user.getID())){ // check internal database, not user values
//                found = true;
//                if (!u.getRole().equals("librarian")){
//                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
//                            ApiResponse("Error, only librarian can change book status"));
//                }
//            }
//        }
//
//        if (!found){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new
//                    ApiResponse("Error, the user does not exist"));
//        }

        if (!user.getRole().equals("librarian")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
                    ApiResponse("Error, only librarian can change book status"));
        }

        if (bookService.makeBookUnavailable(iD)){
            return ResponseEntity.status(HttpStatus.OK).body(new
                    ApiResponse("Book status changed to unavailable successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error, not found."));
        }

    }
}
