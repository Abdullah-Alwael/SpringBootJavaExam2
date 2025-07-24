package com.spring.boot.springbootjavaexam2.Service;

import com.spring.boot.springbootjavaexam2.Model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BookService {
    ArrayList<Book> books = new ArrayList<>();

    public ArrayList<Book> getAllBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public boolean updateBook(String iD, Book book) {
        for (Book b : books) {
            if (b.getID().equals(iD)) {
                books.set(books.indexOf(b), book);
                return true;
            }
        }

        return false;
    }

    public boolean deleteBook(String iD) {
        for (Book b : books) {
            if (b.getID().equals(iD)) {
                books.remove(b);
                return true;
            }
        }

        return false;
    }

    public Book searchBookByName(String name) {
        for (Book b : books) {
            if (b.getName().contains(name)) {
                return b;
            }
        }

        return null;
    }

    public ArrayList<Book> searchByCategory(String category) {
        ArrayList<Book> categorizedBooks = new ArrayList<>();
        for (Book b : books) {
            if (b.getCategory().equals(category)) {
                categorizedBooks.add(b);
            }
        }

        return categorizedBooks;
    }

    public ArrayList<Book> searchByNumberOfPages(int number) {
        ArrayList<Book> foundByPages = new ArrayList<>();

        for (Book b : books) {
            if (b.getNumber_of_pages() >= number) {
                foundByPages.add(b);
            }
        }

        return foundByPages;
    }

    public boolean makeBookUnavailable(String iD) {
        for (Book b : books) {
            if (b.getID().equals(iD)) {
                b.setAvailable(false);
                return true; // changed successfully
            }
        }

        return false; // not found
    }
}
