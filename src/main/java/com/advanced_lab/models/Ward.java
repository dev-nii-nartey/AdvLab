package com.advanced_lab.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(value= "wards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ward {
    @Id
    private String id;

    private Integer number;
    private Integer numberOfBeds;

    @DBRef(lazy = true)
    private Department department;

    @DBRef(lazy = true)
    private Nurse supervisor;

    @DBRef(lazy = true)
    private List<Hospitalization> hospitalizations;
}
