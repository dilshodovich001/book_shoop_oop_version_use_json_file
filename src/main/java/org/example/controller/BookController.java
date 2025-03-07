package org.example.controller;

import org.example.dto.BookRequest;
import org.example.dto.BookResponse;
import org.example.service.BookService;

import java.util.List;
import java.util.UUID;

public class BookController {
    private final BookService bookService = new BookService();
    public void addBook(BookRequest request) {
        bookService.addBook(request);
    }

    public List<BookResponse> showBook(String lan) {
        return bookService.showBook(lan);
    }

    public boolean deleteBook(UUID uuid) {
        return bookService.deleteBook(uuid);
    }
}
