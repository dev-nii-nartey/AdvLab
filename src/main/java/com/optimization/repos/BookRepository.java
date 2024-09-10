package com.optimization.repos;

import com.optimization.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}





















//
//public interface BookRepository extends JpaRepository<Book, Long> {
//    List<Book> findByGenreIgnoreCase(String genre);
//}