//package com.optimization.controller;
//
//import com.optimization.model.Book;
//import com.optimization.service.IBookServiceImpl;
//import jakarta.validation.Valid;
//import lombok.AllArgsConstructor;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@Slf4j
//@RestController
//@RequestMapping("/api/books")
//@AllArgsConstructor
//public class BookController {
//    private IBookServiceImpl bookService;
//
//    @GetMapping
////   @Cacheable("allBooksCache")
//    public List<Book> getAllBooks() {
//        return bookService.getAllBooks();
//    }
//
//    @GetMapping("/recommendations/{genre}")
////    @Cacheable(value = "recommendationsCache", key = "#genre")
//    public List<Book> getRecommendations(@PathVariable String genre) {
//        return bookService.getRecommendations(genre);
//    }
//
//
//    @GetMapping("/{id}")
////    @Cacheable(value = "recommendationsCache", key = "#genre")
//    public Optional<Book> getBookByid(@PathVariable Long id) {
//        Optional<Book> book = bookService.getBookById(id);
//
//        if(book.isEmpty()) {
//            log.error("Book with id: "+id+" not found");
//            return Optional.empty();
//        }
//        return book;
//    }
//
//
//    @PostMapping
////    @CacheEvict(value = {"allBooksCache", "recommendationsCache"}, allEntries = true)
//    public String addBook(@Valid @RequestBody Book book) {
//       return bookService.addBook(book);
//    }
//}
//
//
//
//
