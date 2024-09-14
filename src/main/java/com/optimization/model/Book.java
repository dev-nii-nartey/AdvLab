package com.optimization.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;

@Data
@Entity
@Table(indexes = @Index(name = "idx_genre", columnList = "genre"))
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title field is required")
    private String title;

    @NotBlank(message = "Title field is required")
    private String author;

    @NotBlank(message = "Title field is required")
    private String genre;

}
