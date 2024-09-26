package com.optimization.service;

import com.optimization.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class IBookServiceImpl implements IBookService {

    private final List<Book> datastore = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong();

    @Override
    public List<Book> getAllBooks() {
        return new ArrayList<>(datastore);
    }

    @Override
    public Book addBook(Book book) {
        if (book == null || book.getTitle() == null || book.getAuthor() == null) {
            return null;
        }
        book.setId(idGenerator.incrementAndGet());
        datastore.add(book);
        return book;
    }


    @Override
    public void deleteBook(Long id) {
        datastore.removeIf(book -> book.getId().equals(id));
    }

    @Override
    public Book findBookById(Long id) {
        return datastore.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
