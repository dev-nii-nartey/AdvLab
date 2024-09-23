package com.optimization.service;

import com.optimization.exception.BookAlreadyExistException;
import com.optimization.model.Book;
import com.optimization.repos.BookRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@AllArgsConstructor
@Service
public class IBookServiceImpl implements IBookService {


    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        log.info("Getting all books from database");
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getRecommendations(String genre) {
        log.info("Getting recommendations from database");
        return bookRepository.findByGenreIgnoreCase(genre);
    }


    @Override
    public Optional<Book> getBookById(Long id) {
        log.info("Getting Book with id "+ id +" from database");
        return bookRepository.findById(id);
    }


    @Override
    @Transactional
    public String addBook(Book book) {
        Optional<Book> existingBook = bookRepository.findByTitleAndAuthor(book.getTitle(), book.getAuthor());
        if (existingBook.isPresent()) {
            log.info("Book already exists");
            throw new BookAlreadyExistException("A book with the title: " + book.getTitle() + " by author: " + book.getAuthor() + " already exists");
        }
        bookRepository.save(book);
        return book.getTitle() + " is created successfully";
    }


}


