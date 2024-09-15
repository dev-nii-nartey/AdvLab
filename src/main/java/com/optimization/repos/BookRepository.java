package com.optimization.repos;

import com.optimization.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByGenreIgnoreCase(String genre);
}




















////////////////--------OPTIMIZED REPOSITORY--------//////////////////////
//@Repository
//public interface BookRepository extends JpaRepository<Book, Long> {
//    List<Book> findByGenreIgnoreCase(String genre);
//}