package com.advanced_lab.models;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(value = "doctors")
@Data
@SuperBuilder
@AllArgsConstructor
public class Doctor extends Employee {
    private String specialty;

    @DBRef(lazy = true)
    private Department department;

    public Doctor() {
       super.setEmployeeType("Doctor");
    }
}
