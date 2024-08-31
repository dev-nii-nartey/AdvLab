package com.advanced_lab.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(value ="departments")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    private String id;

    private String code;
    private String name;
    private String building;

    @DBRef(lazy = true)
    private Doctor director;

    @DBRef(lazy = true)
    private List<Ward> wards;

    @DBRef(lazy = true)
    private List<Nurse> nurses;
}
