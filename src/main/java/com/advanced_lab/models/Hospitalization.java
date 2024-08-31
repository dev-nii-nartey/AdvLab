package com.advanced_lab.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "hospitalizations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hospitalization {
    @Id
    private String id;

    private Integer bedNumber;
    private String diagnosis;

    @DBRef(lazy = true)
    private Patient patient;

    @DBRef(lazy = true)
    private Ward ward;

    @DBRef(lazy = true)
    private Doctor treatingDoctor;
}
