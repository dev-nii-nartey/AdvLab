package com.optimization.service;

import com.optimization.exception.BookAlreadyExistException;
import com.optimization.model.Book;
import com.optimization.repos.BookRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class IBookServiceImpl implements IBookService {


    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getRecommendations(String genre) {
        return bookRepository.findByGenreIgnoreCase(genre);
    }

    @Override
    @Transactional
    public String addBook(Book book) {
        Optional<Book> existingBook = bookRepository.findByTitleAndAuthor(book.getTitle(), book.getAuthor());
        if (existingBook.isPresent()) {
            throw new BookAlreadyExistException("A book with the title: " + book.getTitle() + " by author: " + book.getAuthor() + " already exists");
        }
        bookRepository.save(book);
        return book.getTitle() + " is created successfully";
    }
}


