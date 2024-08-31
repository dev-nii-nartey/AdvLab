package com.advanced_lab.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(value = "patients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    private String patientNumber;

    private String name;
    private String surname;
    private String address;
    private String telephoneNumber;

    @DBRef(lazy = true)
    private List<Hospitalization> hospitalizations;
}
