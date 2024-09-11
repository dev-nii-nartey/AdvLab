package com.optimization.controller;

import com.optimization.model.Book;
import com.optimization.service.IBookServiceImpl;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookController {
    private IBookServiceImpl bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/recommendations/{genre}")
    public List<Book> getRecommendations(@PathVariable String genre) {
        return bookService.getRecommendations(genre);
    }


    @PostMapping
    public void addBook(@RequestBody Book book) {
        bookService.addBook(book);
    }
}
