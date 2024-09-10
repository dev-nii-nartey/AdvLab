package com.optimization.service;

import com.optimization.model.Book;
import com.optimization.repos.BookRepository;

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
}
