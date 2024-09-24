//package com.optimization.repos;
//
//import com.optimization.model.Book;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface BookRepository extends JpaRepository<Book, Long> {
//    List<Book> findByGenreIgnoreCase(String genre);
//
//    Optional<Book> findByTitleAndAuthor(String title, String author);
//}