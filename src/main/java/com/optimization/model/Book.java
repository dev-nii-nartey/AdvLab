package com.optimization.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;

@Data
@Entity
@Table(indexes = @Index(name = "idx_genre", columnList = "genre"))
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String genre;

}














//@Entity
//@Table(indexes = @Index(name = "idx_genre", columnList = "genre"))
//public class Book {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String title;
//    private String author;
//    private String genre;
//
//    // Getters and setters
//}