package com.optimization.service;

import com.optimization.model.Book;

import java.util.List;

public interface IBookService {
    List<Book> getAllBooks();
    List<Book> getRecommendations(String genre);
    String addBook(Book book);
}
