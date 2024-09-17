package com.optimization.controller;

import com.optimization.model.Book;
import com.optimization.service.IBookServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookController {
    private IBookServiceImpl bookService;

    @GetMapping
//    @Cacheable("allBooksCache")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/recommendations/{genre}")
//    @Cacheable(value = "recommendationsCache", key = "#genre")
    public ResponseEntity<List<Book>> getRecommendations(@PathVariable String genre) {
        return ResponseEntity.ok(bookService.getRecommendations(genre));
    }


    @PostMapping
//    @CacheEvict(value = {"allBooksCache", "recommendationsCache"}, allEntries = true)
    public String addBook(@Valid @RequestBody Book book) {
        return bookService.addBook(book);
    }
}




