package com.optimization.service;

import com.optimization.model.Book;
import com.optimization.repos.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public class IBookServiceImpl implements IBookService {
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getRecommendations(String genre) {
        List<Book> allBooks = getAllBooks();

        // Simulate a complex and inefficient recommendation algorithm
        try {
            Thread.sleep(1000); // Simulate a delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return allBooks.stream()
                .filter(book -> book.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }


    @Override
    public Book addBook() {
        return bookRepository.save(new Book());
    }
}












//
//@Service
//public class BookService {
//
//    private BookRepository bookRepository;
//@Override
//    @Cacheable("allBooks")
//    public List<Book> getAllBooks() {
//        return bookRepository.findAll();
//    }
//@Override
//    @Cacheable(value = "recommendations", key = "#genre")
//    public List<Book> getRecommendations(String genre) {
//        return bookRepository.findByGenreIgnoreCase(genre);
//    }
//
//@Override
//    @CacheEvict(value = {"allBooks", "recommendations"}, allEntries = true)
//public Book addBook() {
//    return bookRepository.save(new Book());
//}
//}
