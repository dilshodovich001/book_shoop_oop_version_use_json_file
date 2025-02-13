package org.example.controller;

import org.example.dto.BookRequest;
import org.example.service.BookService;

public class BookController {
    private final BookService bookService = new BookService();
    public void addBook(BookRequest request) {
        bookService.addBook(request);
    }
}
