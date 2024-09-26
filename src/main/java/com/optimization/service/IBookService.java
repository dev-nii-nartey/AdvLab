package com.optimization.service;

import com.optimization.model.Book;
import java.util.List;

public interface IBookService {
    List<Book> getAllBooks();
    Book addBook(Book book);
    void deleteBook(Long id);
    Book findBookById(Long id);
}
