package com.optimization.controller;

import com.optimization.model.Book;
import com.optimization.service.IBookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
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


    @PostMapping("/clear-cache")
    public void addBook() {
        bookService.addBook();
    }
}
