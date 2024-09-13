package com.optimization.service;

import com.optimization.model.Book;
import com.optimization.repos.BookRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class IBookServiceImpl implements IBookService {


    private BookRepository bookRepository;

    @Override
    @Cacheable("allBooks")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getRecommendations(String genre) {
        List<Book> allBooks = getAllBooks();
        return allBooks.stream()
                .filter(book -> book.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }


    @Override
    public void addBook(Book book) {
        bookRepository.save(book);
    }
}


////*********  OPTIMIZED BOOK SERVICE   ************///

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
//    public void addBook(Book book) {
//        bookRepository.save(book);
//}
//}
