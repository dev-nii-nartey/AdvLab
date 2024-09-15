package com.optimization.controller;

import com.optimization.model.Book;
import com.optimization.service.IBookServiceImpl;
import lombok.AllArgsConstructor;


import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookController {
    private IBookServiceImpl bookService;

    @GetMapping
    @Cacheable("allbooks")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/recommendations/{genre}")
    @Cacheable(value = "recommendations", key = "#genre")
    public List<Book> getRecommendations(@PathVariable String genre) {
        return bookService.getRecommendations(genre);
    }


    @PostMapping
    @CacheEvict("allbooks")
    public void addBook(@RequestBody Book book) {
        bookService.addBook(book);
    }
}






////*********  OPTIMIZED CONTROLLER ************///

//@RestController
//@RequestMapping("/api/books")
//@AllArgsConstructor
//public class BookController {
//    private IBookServiceImpl bookService;
//
//    @GetMapping
//    @Cacheable("allbooks")
//    public List<Book> getAllBooks() {
//        return bookService.getAllBooks();
//    }
//
//    @GetMapping("/recommendations/{genre}")
//    public List<Book> getRecommendations(@PathVariable String genre) {
//        return bookService.getRecommendations(genre);
//    }
//
//
//    @PostMapping
//    @CacheEvict("allbooks")
//    public void addBook(@RequestBody Book book) {
//        bookService.addBook(book);
//    }
//}
